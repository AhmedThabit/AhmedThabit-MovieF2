package demo.example.com.customarrayadapter;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements Communicator {
    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    private boolean mTwoPane;
    ArrayList<MyMovie> myCommMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check
        if (findViewById(R.id.fragment_content) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_content, new detailsActivityFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void response(ArrayList<MyMovie> myMovie, int i) {

        Bundle args = new Bundle();
        args.putString("pic", myMovie.get(i).getImage());
        args.putString("tit", myMovie.get(i).getTitle());
        args.putString("date", myMovie.get(i).getRelease_date());
        args.putString("vote", myMovie.get(i).getVote_average());
        args.putString("overView", myMovie.get(i).getOverView());
        args.putString("id", myMovie.get(i).getId());


        Intent intent = new Intent(this, detailsActivity.class);
        intent.putExtras(args);
/*
        intent.putExtra("pic", myMovie.get(i).getImage())
                .putExtra("tit", myMovie.get(i).getTitle())
                .putExtra("date",  myMovie.get(i).getRelease_date())
                .putExtra("vote", myMovie.get(i).getVote_average())
                .putExtra("overView", myMovie.get(i).getOverView())
                .putExtra("id", myMovie.get(i).getId());
*/

        if (mTwoPane) {

            Bundle bundle = new Bundle();
            bundle.putString("pic", myMovie.get(i).getImage());
            bundle.putString("tit", myMovie.get(i).getTitle());
            bundle.putString("date",  myMovie.get(i).getRelease_date());
            bundle.putString("vote", myMovie.get(i).getVote_average());
            bundle.putString("overView", myMovie.get(i).getOverView());
            bundle.putString("id", myMovie.get(i).getId());

            FragmentManager manager = getFragmentManager();
            //detailsActivityFragment f2=(F) manager.findFragmentById(R.id.fragment_content);

            detailsActivityFragment fragment = new detailsActivityFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_content, fragment, DETAILFRAGMENT_TAG)
                    .commit();


        } else {

            startActivity(intent);

        }
    }
}
