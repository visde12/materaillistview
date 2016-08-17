package com.example.acer.myapplication.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.acer.myapplication.Adapter.MoviesAdapter;
import com.example.acer.myapplication.Models.Biodata;
import com.example.acer.myapplication.Models.Movie;
import com.example.acer.myapplication.R;
import com.example.acer.myapplication.Utils.Constants;
import com.example.acer.myapplication.Utils.DividerItemDecoration;
import com.example.acer.myapplication.Utils.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Acer on 16-08-2016.
 */
public class ListView extends Activity {


    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    public static  ArrayList<Biodata> biodatalist;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        biodatalist = new ArrayList<Biodata>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        new GetContacts().execute();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Biodata movie = biodatalist.get(position);
                Toast.makeText(getApplicationContext(), movie.getFName()+ " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

       // prepareMovieData();
    }

    private void prepareMovieData() {

        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListView.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListView.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListView.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Constants.BREAKING_NEWS, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

           // {"id":1,"first_name":"Harry","last_name":"Hughes",
               //     "email":"hhughes0@dot.gov","gender":"Male","imageurl":"http://dummyimage.com/126x139.png/dddddd/000000"},
                if (jsonStr != null) {
                try {
                    JSONArray jsonaray = new JSONArray(jsonStr);
                    for (int i=0;i<jsonaray.length();i++){
                        JSONObject jo = jsonaray.getJSONObject(i);
                        String id= jo.getString("id");
                        String firstname=jo.getString("first_name");
                        String lastname= jo.getString("last_name");
                        String email=jo.getString("email");
                        String gender=jo.getString("gender");
                        String imageurl=jo.getString("imageurl");

                        biodatalist.add(new Biodata(id,firstname,lastname,email,gender,imageurl));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            mAdapter = new MoviesAdapter(biodatalist);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

           /* ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
                    TAG_PHONE_MOBILE }, new int[] { R.id.name,
                    R.id.email, R.id.mobile });

            setListAdapter(adapter);*/
        }

    }
}
