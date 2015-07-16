package com.example.ale.groceryshoppingassistant;


import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TabHost tabHost;
    private GridView grid;
    public static final String EXTRA_POS = "POS";
    public static final String EXTRA_IMAGE_ID = "ID";
    private URLDataBaseManager manager;
    private TaskDBHelper helper;
    private SimpleCursorAdapter cursorAdapter;
    private ListView tasksList;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);

        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Tab1").setIndicator("Nutritional Values").setContent(R.id.tab1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab2").setIndicator("Grocery shopping List").setContent(R.id.tab2);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new ImageAdapter(this));

        manager = new URLDataBaseManager(this);
        crearURLs();

        grid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                intent.putExtra(EXTRA_POS, position);
                intent.putExtra(EXTRA_IMAGE_ID, Grid.getData()[position]);
                startActivity(intent);
            }
        });

        //tasksList = (ListView)findViewById(R.id.tasksList);
        updateUI();
    }

    public void addTask(View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Add");
        builder.setMessage("What do you want to buy?");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String task = inputField.getText().toString();

                helper = new TaskDBHelper(MainActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.clear();
                values.put(TaskDBHelper.TaskContract.Columns.TASK,task);

                db.insertWithOnConflict(TaskDBHelper.TaskContract.TABLE,null,values,SQLiteDatabase.CONFLICT_IGNORE);
                updateUI();
            }
        });

        builder.setNegativeButton("Cancel",null);

        builder.create().show();
    }


    public void updateUI(){
        helper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TaskDBHelper.TaskContract.TABLE,
                new String[]{TaskDBHelper.TaskContract.Columns._ID, TaskDBHelper.TaskContract.Columns.TASK},
                null, null, null, null, null);

        cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.tasks,
                cursor,
                new String[]{TaskDBHelper.TaskContract.Columns.TASK},
                new int[]{R.id.taskTextView},
                0
        );
        tasksList = (ListView)findViewById(R.id.tasksList);
        tasksList.setAdapter(cursorAdapter);
    }

    public void onDoneButtonClick(View view){
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                TaskDBHelper.TaskContract.TABLE,
                TaskDBHelper.TaskContract.Columns.TASK,
                task);


        helper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql);
        updateUI();
    }

    public void crearURLs(){
        manager.insertar("apple", "https://api.nutritionix.com/v1_1/search/apple?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("orange","https://api.nutritionix.com/v1_1/search/orange?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("pineapple","https://api.nutritionix.com/v1_1/search/pineapple?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("papaya","https://api.nutritionix.com/v1_1/search/papaya?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("lemons","https://api.nutritionix.com/v1_1/search/lemon?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("strawberries","https://api.nutritionix.com/v1_1/search/strawberry?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("watermelon","https://api.nutritionix.com/v1_1/search/watermelon?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("pears","https://api.nutritionix.com/v1_1/search/pear?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("peaches","https://api.nutritionix.com/v1_1/search/peach?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("grapes","https://api.nutritionix.com/v1_1/search/grapes?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("banana","https://api.nutritionix.com/v1_1/search/banana?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("lettuce","https://api.nutritionix.com/v1_1/search/lettuce?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("cucumber","https://api.nutritionix.com/v1_1/search/cucumber?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("carrots","https://api.nutritionix.com/v1_1/search/carrot?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("tomato","https://api.nutritionix.com/v1_1/search/tomato?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("cabbage","https://api.nutritionix.com/v1_1/search/cabbage?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("onions","https://api.nutritionix.com/v1_1/search/onion?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("avocado","https://api.nutritionix.com/v1_1/search/avocado?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("broccoli","https://api.nutritionix.com/v1_1/search/broccoli?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("white sugar","https://api.nutritionix.com/v1_1/search/white%20sugar?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("brown sugar","https://api.nutritionix.com/v1_1/search/brown%20sugar?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("salt","https://api.nutritionix.com/v1_1/search/salt?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("rice","https://api.nutritionix.com/v1_1/search/rice?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("potatoes","https://api.nutritionix.com/v1_1/search/potato?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("beans","https://api.nutritionix.com/v1_1/search/beans?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("chicken","https://api.nutritionix.com/v1_1/search/chicken?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("beeef","https://api.nutritionix.com/v1_1/search/beef?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
        manager.insertar("pork","https://api.nutritionix.com/v1_1/search/pork?fields=item_name%2Citem_id%2Cnf_calories%2Cnf_total_fat&appId=62d40521&appKey=1cf7bb713fa134e01db728a24bb31157");
    }
}
