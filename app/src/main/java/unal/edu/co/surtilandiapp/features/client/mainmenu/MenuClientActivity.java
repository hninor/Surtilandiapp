package unal.edu.co.surtilandiapp.features.client.mainmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.SupportMapFragment;

import unal.edu.co.surtilandiapp.R;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.CompetitorFragment;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.DataModel;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.DrawerItemCustomAdapter;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.ProductsFragment;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.ProfileFragment;
import unal.edu.co.surtilandiapp.features.shopkeeper.navigationdrawer.SearchStoreFragment;

public class MenuClientActivity extends AppCompatActivity {


    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    SupportMapFragment sMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[5];

        drawerItem[0] = new DataModel(R.drawable.ic_shop, "Tiendas");
        drawerItem[1] = new DataModel(R.drawable.ic_products, "Productos");
        drawerItem[2] = new DataModel(R.drawable.ic_award, "Información perfil");
        drawerItem[3] = new DataModel(R.drawable.ic_question, "Ayuda");
        drawerItem[4] = new DataModel(R.drawable.ic_quit, "Salir");

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

//        sMapFragment.getMapAsync(this);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;
//        android.support.v4.app.Fragment mFragment = null;
//        android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();
        switch (position) {
            case 0:
                //Load Map
//                Intent intent = new Intent(MenuShopKeeperActivity.this, SearchStore.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                fragment = new SearchStoreFragment();
                break;
            case 1:
                fragment = new ProductsFragment();
                break;
            case 2:
                fragment = new CompetitorFragment();
                break;
            case 3:
                fragment = new ProfileFragment();
                break;
            case 4:
                finish();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MenuShopKeeperActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

}