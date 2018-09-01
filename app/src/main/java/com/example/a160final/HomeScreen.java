package com.example.a160final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * Created by badiparvaneh on 4/18/18.
 */

public class HomeScreen extends AppCompatActivity {

private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private FirebaseDatabase database;
    private DatabaseReference categoryRef;
    private ArrayList<Category> mCategories = new ArrayList<>();
    byte[] byteArray = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        RelativeLayout layout;
        TextView search;
        FloatingActionButton userIcon;
        LinearLayout linear;


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.order_now) {
                    //jump to order now activity
                    Intent i = new Intent(HomeScreen.this, HomeScreen.class);
                    if(byteArray!=null){i.putExtra("image",byteArray);}
                    startActivity(i);
                } else if (id == R.id.payment) {
                    //jump to payment activity
                    Intent i = new Intent(HomeScreen.this,PaymentActivity.class);
                    if(byteArray!=null){i.putExtra("image",byteArray);}
                    startActivity(i);

                } else if (id == R.id.order_history) {
                    //jump to order history activity
                    Intent i = new Intent(HomeScreen.this,OrderHistoryActivity.class);
                    if(byteArray!=null){i.putExtra("image",byteArray);}
                    startActivity(i);
                }
                else if (id == R.id.settings) {
                    //jump to settings activity
                    Intent i = new Intent(HomeScreen.this, settingActivity.class);
                    if(byteArray!=null){i.putExtra("image",byteArray);}
                    startActivity(i);
                }
                else if (id == R.id.check_status) {
                    //jump to settings activity
                    Intent i = new Intent(HomeScreen.this,CheckStatusActivity.class);
                    if(byteArray!=null){i.putExtra("image",byteArray);}
                    startActivity(i);
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });

        ImageView userImageHomeScreen = navigationView.getHeaderView(0).findViewById(R.id.imageViewUser);
        userIcon = findViewById(R.id.userIcon);


        userIcon.setImageResource(R.drawable.user_icon);

        byteArray = getIntent().getByteArrayExtra("image");
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            userImageHomeScreen.setImageBitmap(bitmap);
            userIcon.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(HomeScreen.this, HomeScreenMenu.class);
                //startActivity(i);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                String userName = ((UserGlobal) HomeScreen.this.getApplication()).getUser().getName();
                TextView t = findViewById(R.id.menu_username);
                t.setText(userName);
            }
        });

        layout = findViewById(R.id.home_cell_relative);
        //search = findViewById(R.id.search_field);
        userIcon = findViewById(R.id.userIcon);
        linear = findViewById(R.id.home_linear);




        database = FirebaseDatabase.getInstance();
        final DatabaseReference categoryRef = database.getReference().child("Categories");

        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.hasChild("Categories")) {
                    database.getReference().child("Categories");
                }
                if (true) {
                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    //Restaurant 1
                    ArrayList<Food> pizza1Menu = new ArrayList<>();
                    pizza1Menu.add(new Food("Cheese Pizza - Slice", "$3.50", 3.50, "Tomato sauce, cheese blend", R.drawable.cheese_slice));
                    pizza1Menu.add(new Food("Cheese Pizza", "$10.00", 10.00, "Tomato sauce, cheese blend", R.drawable.cheese));
                    pizza1Menu.add(new Food("Pepperoni Pizza - Slice", "$4.00", 4.00, "Tomato sauce, cheese blend, pepperoni", R.drawable.pep_slice));
                    pizza1Menu.add(new Food("Pepperoni Pizza", "$13.00", 13.00, "Tomato sauce, cheese blend, pepperoni", R.drawable.pepperoni));
                    pizza1Menu.add(new Food("Hawaiian Pizza - Slice", "$4.50", 4.50, "Tomato sauce, cheese blend, pineapple, ham", R.drawable.hw_slice));
                    pizza1Menu.add(new Food("Hawaiian Pizza", "$15.00", 15.00, "Tomato sauce, cheese blend, pineapple, ham", R.drawable.hw));
                    pizza1Menu.add(new Food("Sausage Pizza - Slice", "$4.50", 4.50, "Tomato sauce, cheese blend, pork sausage", R.drawable.sausage_slice));
                    pizza1Menu.add(new Food("Sausage Pizza", "$15.00", 15.00, "Tomato sauce, cheese blend, pork sausage", R.drawable.sausage));
                    pizza1Menu.add(new Food("Vegetarian Pizza - Slice", "$4.50", 4.50, "Tomato sauce, cheese blend, onion, mushroom, bell pepper", R.drawable.veg_slice));
                    pizza1Menu.add(new Food("Vegetarian Pizza", "$15.00", 15.00, "Tomato sauce, cheese blend, onion, mushroom, bell pepper", R.drawable.veg));
                    pizza1Menu.add(new Food("Supreme Pizza - Slice", "$5.00", 5.00, "Tomato sauce, cheese blend, pepperoni, sausage, mushroom, bell pepper", R.drawable.supreme_slice));
                    pizza1Menu.add(new Food("Supreme Pizza", "$18.00", 18.00, "Tomato sauce, cheese blend, pepperoni, sausage, mushroom, bell pepper", R.drawable.supreme));
                    pizza1Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    pizza1Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    pizza1Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant pizza1 = new Restaurant("Slices and Suds", pizza1Menu);

                    ArrayList<Food> pizza2Menu = new ArrayList<>();
                    pizza2Menu.add(new Food("Sweet Chili Sriracha Chicken Pizza", "$6.00", 6.00, "Roasted red peppers, green onions and free range chicken", R.drawable.siracha_pizza));
                    pizza2Menu.add(new Food("Chicken Bacon Ranch Pizza", "$6.00", 6.00, "Chicken, bacon, cheese blend, side of ranch", R.drawable.chicken_bacon_pizza));
                    pizza2Menu.add(new Food("Shrimp Pizza", "$7.00", 7.00, "Tomato sauce, cheese blend, shrimp", R.drawable.shrimp_pizza));
                    pizza2Menu.add(new Food("BBQ Chicken", "$7.00", 7.00, "BBQ sauce, chicken, onion, cheese blend", R.drawable.bbq_chicken_pizza));
                    pizza2Menu.add(new Food("Artichoke Bacon", "$7.00", 7.00, "Creamy white sauce, artichoke hearts, bacon, cheese blend", R.drawable.artichoke_pizza));
                    pizza2Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    pizza2Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    pizza2Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant pizza2 = new Restaurant("Farina Pizza", pizza2Menu);
                    restaurants.add(pizza1);
                    restaurants.add(pizza2);
                    Category pizza = new Category("Pizza", R.drawable.pizza, restaurants);

                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Pizza").setValue(pizza);
                }

                if (true) {
                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    ArrayList<Food> burger1Menu = new ArrayList<>();
                    burger1Menu.add(new Food("Signature Burger", "$6.00", 6.00, "Beef burger, lettuce, tomato, onion, Signature sauce", R.drawable.sig_burger));
                    burger1Menu.add(new Food("Signature Double Burger", "$10.00", 10.00, "Double beef burger, lettuce, tomato, onion, Signature sauce", R.drawable.sig_double));
                    burger1Menu.add(new Food("Signature Cheeseburger", "$7.00", 7.00, "Beef burger, cheddar cheese, lettuce, tomato, onion, Signature sauce", R.drawable.sig_cheese));
                    burger1Menu.add(new Food("Signature Double Cheeseburger", "$12.00", 12.00, "Double beef burger, cheddar cheese, lettuce, tomato, onion, Signature sauce", R.drawable.sig_double_cheese));
                    burger1Menu.add(new Food("Bacon Cheeseburger", "$8.00", 8.00, "Beef burger, bacon, cheddar cheese, lettuce, tomato, onion", R.drawable.bacon_cheese));
                    burger1Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    burger1Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    burger1Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant1 = new Restaurant("Fuelburger", burger1Menu);

                    ArrayList<Food> burger2Menu = new ArrayList<>();
                    burger2Menu.add(new Food("Jalapeno Burger", "$7.00", 7.00, "Beef burger, Jalapenos, lettuce, tomato, onion, Signature sauce", R.drawable.jal_burger));
                    burger2Menu.add(new Food("Chipotle Bacon Burger", "$10.00", 10.00, "Chipotle sauce, beef burger, bacon, lettuce, tomato", R.drawable.chipotle_burger));
                    burger2Menu.add(new Food("Cheeseburger", "$6.00", 6.00, "Beef burger, cheddar cheese, lettuce, tomato, onion", R.drawable.cheeseburger));
                    burger2Menu.add(new Food("BBQ Chicken Sandwich", "$7.00", 7.00, "Grilled chicken, Swiss cheese, lettuce, tomato, bbq sauce", R.drawable.bbq_chick_sand));
                    burger2Menu.add(new Food("Bacon Cheeseburger", "$7.50", 7.50, "Beef burger, bacon, American cheese, lettuce, tomato, onion", R.drawable.bacon_cheese));
                    burger2Menu.add(new Food("Make it a meal", "+$4.00", 4.00, "Medium fries and soda", R.drawable.meal));
                    burger2Menu.add(new Food("French Fries", "$3.00", 3.00, "French fries with signature sauce", R.drawable.fries));
                    burger2Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    burger2Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    burger2Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant2 = new Restaurant("BurgerQueen", burger2Menu);

                    //add menu 3 and rest3 smoakin Oakland Grill
                    ArrayList<Food> burger3Menu = new ArrayList<>();

                    burger3Menu.add(new Food("Dub-Peat Burger", "$8.00", 8.00, "Aged Cheddar, beefsteak tomato, lettuce, pickle, onion, Thousand Island", R.drawable.dub_peat));
                    burger3Menu.add(new Food("Beat-Down Burger", "$8.00", 8.00, "Caramelized red wine onions, bacon, blue & Gruyère cheeses, baby arugula and garlic aioli", R.drawable.beat_down));
                    burger3Menu.add(new Food("Cheeseburger", "$6.00", 6.00, "Beef burger, cheese, lettuce, tomato", R.drawable.cheeseburger));
                    burger3Menu.add(new Food("Bacon Cheeseburger", "$7.50", 7.50, "Beef burger, bacon, American cheese, lettuce, tomato, onion", R.drawable.bacon_cheese));
                    burger3Menu.add(new Food("Make it a meal", "+$5.00", 5.00, "Medium fries and soda", R.drawable.meal));
                    burger3Menu.add(new Food("French Fries", "$4.00", 4.00, "French fries", R.drawable.fries));
                    burger3Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    burger3Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    burger3Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant3 = new Restaurant("Smokin Oakland Grill", burger3Menu);

                    restaurants.add(restaurant1);
                    restaurants.add(restaurant2);
                    restaurants.add(restaurant3);

                    Category burger = new Category("Burgers", R.drawable.bruger, restaurants);


                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Burgers").setValue(burger);

                }

                if (true) {
                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    ArrayList<Food> sandwich1Menu = new ArrayList<>();

                    sandwich1Menu.add(new Food("Turkey Sandwich", "$8.00", 8.00, "Swiss cheese, organic turkey breast, lettuce, tomato, mustard", R.drawable.turkey_sand));
                    sandwich1Menu.add(new Food("BLT Sandwich", "$7.00", 7.00, "Bacon, American cheese, lettuce, tomato, mayonnaise", R.drawable.blt));
                    sandwich1Menu.add(new Food("Meatball Sandwich", "$7.00", 7.00, "Pork meatballs, Mozzarella cheese, green peppers, black olives", R.drawable.meatball_sand));
                    sandwich1Menu.add(new Food("Tuna Salad Sandwich", "$8.50", 8.50, "Tuna Salad, Provolone cheese, lettuce, tomato, onion", R.drawable.tuna_sand));
                    sandwich1Menu.add(new Food("Make it a meal", "+$3.00", 3.00, "chips and soda", R.drawable.meal_sand));
                    sandwich1Menu.add(new Food("Kettle Cooked Chips", "$2.00", 2.00, "Original salty potato chips", R.drawable.kettle));
                    sandwich1Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    sandwich1Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    sandwich1Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant1 = new Restaurant("Somkin Oakland Grill", sandwich1Menu);

                    ArrayList<Food> sandwich2Menu = new ArrayList<>();

                    sandwich2Menu.add(new Food("Philly Chicken Sandwich", "$9.00", 9.00, "Swiss cheese, chicken, grilled peppers and onions, dijon mustard", R.drawable.chicken_philly));
                    sandwich2Menu.add(new Food("Philly Cheesesteak Sandwich", "$10.00", 10.00, "Hoagie roll, American cheese, grilled steak, grilled peppers and onions, mayonnaise", R.drawable.steak_philly));
                    sandwich2Menu.add(new Food("Beef Patty Sandwich", "$8.00", 8.00, "Beef patty, Swiss cheese, green peppers, onions", R.drawable.beef_patty));
                    sandwich2Menu.add(new Food("Tuna Salad Sandwich", "$7.50", 7.50, "Tuna Salad, Pepper Jack cheese, lettuce, onion", R.drawable.tuna_sand));
                    sandwich2Menu.add(new Food("Make it a meal", "+$3.00", 3.00, "chips and soda", R.drawable.meal_sand));
                    sandwich1Menu.add(new Food("Homemade Style Chips", "$3.00", 3.00, "Original salty potato chips", R.drawable.kettle));
                    sandwich2Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    sandwich2Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    sandwich2Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant2 = new Restaurant("Al Attles' California Cheesesteak", sandwich2Menu);

                    ArrayList<Food> sandwich3Menu = new ArrayList<>();

                    sandwich3Menu.add(new Food("Club Sandwich", "$9.00", 9.00, "Swiss cheese, turkey, ham, roast beef, lettuce, tomato, sauce", R.drawable.club_sand));
                    sandwich3Menu.add(new Food("Chicken Salad Sandwich", "$8.00", 8.00, "Premium Chicken Salad, American cheese, lettuce, pickles", R.drawable.chicken_salad));
                    sandwich3Menu.add(new Food("Veggie Sandwich", "$6.00", 6.00, "Spinach, Pepper Jack cheese, green peppers, onions, tomato, cucumber, olives", R.drawable.veggie_sand));
                    sandwich3Menu.add(new Food("Pulled Pork Sandwich", "$9.50", 9.50, "Pulled pork, Swiss cheese, coleslaw, bbq sauce", R.drawable.pulled_pork));
                    sandwich3Menu.add(new Food("Make it a meal", "+$3.00", 3.00, "chips and soda", R.drawable.meatball_sand));
                    sandwich3Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    sandwich3Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    sandwich3Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant3 = new Restaurant("Banh Mi Sandwiches", sandwich3Menu);

                    restaurants.add(restaurant1);
                    restaurants.add(restaurant2);
                    restaurants.add(restaurant3);

                    Category sandwiches = new Category("Sandwiches", R.drawable.sandwich, restaurants);


                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Sandwiches").setValue(sandwiches);
                }

                if (true) {

                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    ArrayList<Food> snack1Menu = new ArrayList<>();

                    snack1Menu.add(new Food("Poporn - Small", "$5.00", 5.00, "Small original popcorn", R.drawable.small_popcorn));
                    snack1Menu.add(new Food("Popcorn - Medium", "$7.00", 7.00, "Medium original popcorn", R.drawable.med_popcorn));
                    snack1Menu.add(new Food("Popcorn - Large", "$10.00", 10.00, "Large original popcorn", R.drawable.large_popcorn));
                    snack1Menu.add(new Food("Add butter", "+ $2.00", 2.00, "Add melted butter", R.drawable.butter));
                    snack1Menu.add(new Food("Caramel Popcorn", "$7.00", 7.00, "Single-size caramel popcorn", R.drawable.caramel_popcorn));
                    snack1Menu.add(new Food("Chocolate Popcorn", "8.00", 8.00, "Single-size chocolate-dipped popcorn", R.drawable.chocolate_popcorn));
                    snack1Menu.add(new Food("Cotton Candy", "$5.00", 5.00, "Rainbow cotton candy", R.drawable.cotton_candy));
                    snack1Menu.add(new Food("Strawberry Lemonade 12oz", "$4.00", 4.00, "Strawberry lemonade with fresh pieces of strawbery - 12 oz", R.drawable.strawberr_lemonade));

                    Restaurant restaurant1 = new Restaurant("Popcorn Cart", snack1Menu);

                    ArrayList<Food> snack2Menu = new ArrayList<>();

                    snack2Menu.add(new Food("Banana Split", "$5.00", 5.00, "Banana Split dippin dots ice cream", R.drawable.banana_dd));
                    snack2Menu.add(new Food("Birthday Cake", "$5.00", 5.00, "Birthday Cake dippin dots ice cream", R.drawable.bdcake_dd));
                    snack2Menu.add(new Food("Brownie Batter", "$5.00", 5.00, "Brownie Batter dippin dots ice cream", R.drawable.brownie_dd));
                    snack2Menu.add(new Food("Chocolate Chip Cookie Dough", "$5.00", 5.00, "Chocolate Chip Cookie Dough dippin dots ice cream", R.drawable.choc_chip_dd));
                    snack2Menu.add(new Food("Mint Cookie Crunch", "$5.00", 5.00, "Mint Cookie Crunch dippin dots ice cream", R.drawable.mint_dd));
                    snack2Menu.add(new Food("Cookies 'n Cream", "$5.00", 5.00, "Cookies 'n Cream dippin dots ice cream", R.drawable.cnc_dd));
                    snack2Menu.add(new Food("Make large cup", "+ $2.00", 2.00, "Make the size large", R.drawable.dd_large));

                    Restaurant restaurant2 = new Restaurant("Dippin Dots", snack2Menu);

                    restaurants.add(restaurant1);
                    restaurants.add(restaurant2);

                    Category snacks = new Category("Snacks", R.drawable.popcorn, restaurants);

                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Snacks").setValue(snacks);
                }

                if (true) {


                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    ArrayList<Food> salad1Menu = new ArrayList<>();

                    salad1Menu.add(new Food("Caesar Salad", "$7.00", 7.00, "Romaine lettuce, croutons, parmesan cheese, signature Caesar Sauce", R.drawable.caesar_salad));
                    salad1Menu.add(new Food("Tofu Salad", "$8.00", 8.00, "Lettuce, tofu, olives, tomato, vinegar", R.drawable.tofu_salad));
                    salad1Menu.add(new Food("Greek Salad", "$8.00", 8.00, "Tomato, cucumber, onion, olives, feta cheese, Tzatziki sauce", R.drawable.greek_salad));
                    salad1Menu.add(new Food("Garden Salad", "9.00", 9.00, "Lettuce, spinach, tomato, cucumber, olives, onions, Italian dressing", R.drawable.garden_salad));
                    salad1Menu.add(new Food("Add steak", "+ $4.00", 4.00, "Add steak to you salad", R.drawable.steak_salad));
                    salad1Menu.add(new Food("Add grilled chicken", "+ $3.00", 3.00, "Add grilled chicken to your salad", R.drawable.chick_salad));
                    salad1Menu.add(new Food("Bottled Water", "$3.00", 3.00, "Aquafina drinking water", R.drawable.bottle_water));
                    salad1Menu.add(new Food("Lemonade", "$4.00", 4.00, "Homemade style lemonade", R.drawable.lemonade));

                    Restaurant restaurant1 = new Restaurant("Dub Greens", salad1Menu);

                    ArrayList<Food> salad2Menu = new ArrayList<>();

                    salad2Menu.add(new Food("Arugula Champion", "$8.00", 8.00, "Arugula, lemon zest, tomato, cucumber, Champion dressing", R.drawable.arug_salad));
                    salad2Menu.add(new Food("Cobb Champion", "$10.00", 10.00, "Lettuce, tomato, bacon, grilled chicken, Champion dressing", R.drawable.cobb_salad));
                    salad2Menu.add(new Food("Pasta Champion", "$9.00", 9.00, "Pasta, sun-dried tomato, olives, olive oil, parmesan, creamy sauce", R.drawable.pasta_salad));
                    salad2Menu.add(new Food("Waldorf Champion", "$8.00", 8.00, "Lettuce, green apple, grape, walnut, mayonnaise", R.drawable.waldorf_salad));
                    salad2Menu.add(new Food("Add steak", "+ $5.00", 5.00, "Add char-grilled steak", R.drawable.steak_salad));
                    salad2Menu.add(new Food("Add chicken", "+ $4.00", 4.00, "Add char-grilled chicken", R.drawable.chick_salad));
                    salad2Menu.add(new Food("Organic Cola", "$3.00", 3.00, "organic original cola made from sugar-cane", R.drawable.organic_cola));

                    Restaurant restaurant2 = new Restaurant("Salad Champion", salad2Menu);

                    restaurants.add(restaurant1);
                    restaurants.add(restaurant2);

                    Category salads = new Category("Salads", R.drawable.salad, restaurants);

                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Salads").setValue(salads);
                }

                if (true) {
                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    ArrayList<Food> drink1Menu = new ArrayList<>();

                    drink1Menu.add(new Food("Black Coffee", "$3.00", 3.00, "", R.drawable.black_coffee));
                    drink1Menu.add(new Food("Black Coffee - Iced", "$3.00", 3.00, "Iced black coffee", R.drawable.black_coffee_iced));
                    drink1Menu.add(new Food("Cafe Latte", "$5.00", 5.00, "", R.drawable.cafe_latte));
                    drink1Menu.add(new Food("Cafe Latte - Iced", "$5.00", 5.00, "Iced cafe latte", R.drawable.cafe_latte_iced));
                    drink1Menu.add(new Food("Cafe Mocha", "$5.00", 5.00, "", R.drawable.cafe_mocha));
                    drink1Menu.add(new Food("Cafe Mocha - Iced", "$5.00", 5.00, "Iced cafe mocha", R.drawable.cafe_mocha_iced));
                    drink1Menu.add(new Food("Espresso Single Shot", "3.00", 2.00, "", R.drawable.esp_single));
                    drink1Menu.add(new Food("Espresso Double Shot", "5.00", 5.00, "", R.drawable.esp_double));
                    drink1Menu.add(new Food("Green Tea", "$4.00", 4.00, "", R.drawable.green_tea));
                    drink1Menu.add(new Food("Green Tea - Iced", "$4.00", 4.00, "Iced green tea", R.drawable.green_tea_iced));
                    drink1Menu.add(new Food("Chai Masala", "$4.00", 4.00, "", R.drawable.chai_masala));
                    drink1Menu.add(new Food("Black Tea", "$4.00", 4.00, "", R.drawable.black_tea));

                    Restaurant restaurant1 = new Restaurant("Peet's Coffee", drink1Menu);

                    ArrayList<Food> drink2Menu = new ArrayList<>();

                    drink2Menu.add(new Food("Duvel", "$7.00", 7.00, "Belgian Strong Ale - 16 oz", R.drawable.duvel));
                    drink2Menu.add(new Food("Yorkshire Stingo", "$7.00", 7.00, "Old Ale - 16 oz", R.drawable.yorkshire_stingo));
                    drink2Menu.add(new Food("Pursuit of Hoppiness", "$7.00", 7.00, "American Red Ale - 16 oz", R.drawable.pursuit));
                    drink2Menu.add(new Food("Tripel", "$7.00", 7.00, "Belgian Tripel - 16 oz", R.drawable.tripel));
                    drink2Menu.add(new Food("Sorachi Ace", "$7.00", 7.00, "Saison - 16 oz", R.drawable.sorachi_ace));
                    drink2Menu.add(new Food("312 Urban Wheat Ale", "$7.00", 7.00, "American Wheat - 16 oz", R.drawable.three_tweleve));
                    drink2Menu.add(new Food("Key Lime Pie", "$7.00", 7.00, "Specialty beer - 16 oz", R.drawable.key_lime));
                    drink2Menu.add(new Food("Velvet Merlin", "$7.00", 7.00, "Blended Oatmeal Stout - 16 oz", R.drawable.velvet_merlin));


                    Restaurant restaurant2 = new Restaurant("Shock Top Beer", drink2Menu);


                    ArrayList<Food> drink3Menu = new ArrayList<>();

                    drink3Menu.add(new Food("Dirty Martini", "$9.00", 9.00, "Gin, dry vermouth, olive brine", R.drawable.dirty_martini));
                    drink3Menu.add(new Food("Gin Madras", "$9.00", 9.00, "Gin, cranberry juice, orange juice", R.drawable.gin_madras));
                    drink3Menu.add(new Food("Greyhound", "$9.00", 9.00, "Gin, orange juice", R.drawable.greyhound));
                    drink3Menu.add(new Food("Lime Rickey", "$9.00", 9.00, "Gin, lime juice, carbonated water", R.drawable.lime_rickey));
                    drink3Menu.add(new Food("Bahama Mama", "$9.00", 9.00, "Rum, coconut liqueur, coffee liqueur, pineapple", R.drawable.bahama_mama));
                    drink3Menu.add(new Food("Hurricane", "$9.00", 9.00, "Rum, lime juice, passion fruit syrup", R.drawable.hurricane));
                    drink3Menu.add(new Food("Mojito", "$9.00", 9.00, "Rum, mint, simple syrup, lime", R.drawable.mojito));
                    drink3Menu.add(new Food("Bloody Maria", "$9.00", 9.00, "Tequila, Bloody Mary with celery stalk", R.drawable.bloody_maria));
                    drink3Menu.add(new Food("Margarita", "$9.00", 9.00, "Tequila, frozen margarita, peach", R.drawable.peach_marg));


                    Restaurant restaurant3 = new Restaurant("Cocktail Bar", drink3Menu);

                    ArrayList<Food> drink4Menu = new ArrayList<>();

                    drink4Menu.add(new Food("Coors Banquet", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.coors_banq));
                    drink4Menu.add(new Food("Coors Light", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.coors_light));
                    drink4Menu.add(new Food("Extra Gold Lager", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.extra_gold));
                    drink4Menu.add(new Food("Keystone Ice", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.keystone_ice));
                    drink4Menu.add(new Food("Keystone Light", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.keystone_light));
                    drink4Menu.add(new Food("Keystone Premium", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.keystone_premium));
                    drink4Menu.add(new Food("Coors Non-Alcoholic", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.coors_na));
                    drink4Menu.add(new Food("George Killian's Irish Red", "$6.00", 6.00, "Barley malt, corn syrup, yeast, hop extract", R.drawable.killian));


                    Restaurant restaurant4 = new Restaurant("Coors Light Club", drink4Menu);

                    restaurants.add(restaurant1);
                    restaurants.add(restaurant2);
                    restaurants.add(restaurant3);
                    restaurants.add(restaurant4);

                    Category beverages = new Category("Beverages", R.drawable.drinks, restaurants);

                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Beverages").setValue(beverages);
                }

                if (true) {

                    ArrayList<Restaurant> restaurants = new ArrayList<>();

                    ArrayList<Food> dogNacho1Menu = new ArrayList<>();

                    dogNacho1Menu.add(new Food("Frankfurter Hotdog", "$5.00", 5.00, "beef hotdog, onion, sweet relish, ketchup", R.drawable.frankfurter));
                    dogNacho1Menu.add(new Food("Bockwurst", "$5.00", 5.00, "Pork, veal, milk, egg", R.drawable.buckwurst));
                    dogNacho1Menu.add(new Food("Hot Link", "$5.00", 5.00, "Beef, peppers, CAUTION: EXTRA SPICY", R.drawable.hotlinks));
                    dogNacho1Menu.add(new Food("Smoked Chicken Apple", "$5.00", 5.00, "Chicken, apple bits", R.drawable.smoked_apple));
                    dogNacho1Menu.add(new Food("Chili Nachos", "$7.00", 7.00, "Tortilla chips, chili, beans, tomato, cheese, sour cream", R.drawable.chili_nachos));
                    dogNacho1Menu.add(new Food("Beef Nachos", "$8.00", 8.00, "Tortilla chips, ground beef, beans, tomato, cheese, sour cream", R.drawable.beef_nachos));
                    dogNacho1Menu.add(new Food("Chicken Nachos", "$8.00", 8.00, "Tortilla chips, shredded chicken, beans, tomato, cheese, sour cream", R.drawable.chicken_nachos));
                    dogNacho1Menu.add(new Food("Nachos", "$6.00", 6.00, "Tortilla chips, beans, tomato, cheese, sour cream", R.drawable.nachos));
                    dogNacho1Menu.add(new Food("Jalapeno Nachos", "$7.00", 7.00, "Tortilla chips, jalapenos, beans, tomato, cheese, sour cream", R.drawable.jal_nachos));
                    dogNacho1Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    dogNacho1Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    dogNacho1Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));

                    Restaurant restaurant1 = new Restaurant("Golden State Greats", dogNacho1Menu);

                    ArrayList<Food> dogNacho2Menu = new ArrayList<>();

                    dogNacho2Menu.add(new Food("Premium Steak Nachos", "$10.00", 10.00, "Tortilla chips, steak, jalapeno, tomato, beans, cheese, sour cream, guacamole", R.drawable.steak_nachos));
                    dogNacho2Menu.add(new Food("Premium Chicken Nachos", "$10.00", 10.00, "Tortilla chips, chicken, jalapeno, tomato, beans, cheese, sour cream, guacamole", R.drawable.prem_chick_machos));
                    dogNacho2Menu.add(new Food("Premium Shrimp Nachos", "$12.00", 12.00, "Tortilla chips, shrimp, jalapeno, tomato, beans, cheese, sour cream, guacamole", R.drawable.shrimp_nachos));
                    dogNacho2Menu.add(new Food("Premium Pork Nachos", "$12.00", 12.00, "Tortilla chips, pork, jalapeno, tomato, beans, cheese, sour cream, guacamole", R.drawable.pork_nachos));
                    dogNacho2Menu.add(new Food("Kielbasa Dog", "$7.00", 7.00, "Pork, beef, onions, mustard and ketchup", R.drawable.kielbasa_dog));
                    dogNacho2Menu.add(new Food("Mango Dog", "$7.00", 7.00, "Chicken, mango, paprika, cilantro, mustard and ketchup", R.drawable.mango_dog));
                    dogNacho2Menu.add(new Food("Calabrese Dog", "$7.00", 7.00, "Pork, paprika, chili, fennel,  onions, mustard and ketchup", R.drawable.calabrese_dog));
                    dogNacho2Menu.add(new Food("Pepsi 16oz", "$3.00", 3.00, "Pepsi Cola - 16 oz", R.drawable.pepsi));
                    dogNacho2Menu.add(new Food("Diet Pepsi 16oz", "$3.00", 3.00, "Diet Pepsi Cola - 16 oz", R.drawable.diet_pepsi));
                    dogNacho2Menu.add(new Food("Crush 160z", "$3.00", 3.00, "Crush Orange Cola - 16 oz", R.drawable.crush));


                    Restaurant restaurant2 = new Restaurant("Loaded Nachos, Dogs", dogNacho2Menu);


                    restaurants.add(restaurant1);
                    restaurants.add(restaurant2);

                    Category dogNacho = new Category("Hotdogs & Nachos", R.drawable.dog_nacho, restaurants);



                    //TODO: test this with real-time Firebase to make sure it works
                    categoryRef.child("Hotdogs & Nachos").setValue(dogNacho);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCategories = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int imageID = ds.child("imageId").getValue(Integer.class);
                    String name = ds.child("name").getValue(String.class);
//                    ArrayList<Restaurant> restaurants = ds.child("restaurants").getValue(ArrayList.class);
                    ArrayList<Restaurant> retrievedRestaurants = new ArrayList<>();

                    ArrayList<HashMap> restuarants = (ArrayList) ds.child("restaurants").getValue();

                    for (HashMap restaurant : restuarants) {
                        String restuarantName = (String) restaurant.get("name");
                        ArrayList restaurantMenu = (ArrayList) restaurant.get("menu");
                        ArrayList<Food> menu = new ArrayList<>();

                        for (Object menuItem : restaurantMenu) {
                            HashMap menuItemHashMap = (HashMap) menuItem;
                            double priceDouble = 0.0;
                            if (((HashMap) menuItem).get("priceDouble").getClass() == Double.class) {
                                priceDouble = (double) ((HashMap) menuItem).get("priceDouble");
                            } else {
                                priceDouble = ((Long) ((HashMap) menuItem).get("priceDouble")).doubleValue();
                            }
                            int imageId = ((Long) ((HashMap) menuItem).get("imageId")).intValue();
                            String price = (String) ((HashMap) menuItem).get("price");
                            String itemName = (String) ((HashMap) menuItem).get("name");
                            String info = (String) ((HashMap) menuItem).get("info");

                            menu.add(new Food(itemName, price, priceDouble, info, imageId));

                        }
                        retrievedRestaurants.add(new Restaurant(restuarantName, menu));
                    }
                    mCategories.add(new Category(name, imageID, retrievedRestaurants));
                }

                mRecyclerView = (RecyclerView) findViewById(R.id.home_screen_rec);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeScreen.this));

                mAdapter = new HomeScreenAdapter(HomeScreen.this, mCategories);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
