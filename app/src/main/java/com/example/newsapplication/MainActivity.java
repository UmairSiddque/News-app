package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryOnClick {
//aa0af3af8bbb4442a1bada910b60195b
    RecyclerView categoryRV,newsRV;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Articles> articlesArrayList=new ArrayList<>();
    ArrayList<CategoryModel> categoryModelArrayList=new ArrayList<>();
    CategoryAdapter categoryAdapter;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryRV=findViewById(R.id.categoryRV);
        newsRV=findViewById(R.id.newsRV);
        linearLayoutManager=new LinearLayoutManager(this);
//        categoryRV.setLayoutManager(linearLayoutManager);
        newsRV.setLayoutManager(linearLayoutManager);
        newsAdapter=new NewsAdapter(this,articlesArrayList);
        categoryAdapter=new CategoryAdapter(this,categoryModelArrayList,this::onCategoryClick);
        newsRV.setAdapter(newsAdapter);
        categoryRV.setAdapter(categoryAdapter);

        getCategory();
        getNews("All");
        newsAdapter.notifyDataSetChanged();

    }
    private void getCategory(){
        categoryModelArrayList.add(new CategoryModel("All","https://www.google.com/search?q=news&sxsrf=ALiCzsbOZuaJH2Jd5x1X6VKprJ5uGkfqXA:1663148754187&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj7us71_5P6AhWKR_EDHXOBBpYQ_AUoBHoECAIQBg&biw=1366&bih=625&dpr=1#imgrc=48d-XIqfvEqqTM"));
        categoryModelArrayList.add(new CategoryModel("Science","https://www.google.com/search?q=science&tbm=isch&ved=2ahUKEwjb0qn5_5P6AhUCkRoKHQLbA70Q2-cCegQIABAA&oq=science&gs_lcp=CgNpbWcQA1CrB1i5GGDHG2gAcAB4AYABAIgBAJIBAJgBAKABAaoBC2d3cy13aXotaW1nsAEAwAEB&sclient=img&ei=2aIhY5uCO4KiaoK2j-gL&bih=625&biw=1366#imgrc=ISZpaGeXIB4OxM"));
        categoryModelArrayList.add(new CategoryModel("Technology","https://www.google.com/search?q=technology&tbm=isch&ved=2ahUKEwiuzr6QgZT6AhXhhM4BHXkfCAsQ2-cCegQIABAA&oq=technology&gs_lcp=CgNpbWcQA1DKAljpHmDzIWgAcAB4BIABAIgBAJIBAJgBAKABAaoBC2d3cy13aXotaW1nsAEAwAEB&sclient=img&ei=FqQhY67rO-GJur4P-b6gWA&bih=625&biw=1366#imgrc=-Kn0KVO7xQabPM"));
        categoryModelArrayList.add(new CategoryModel("Sports","https://www.google.com/search?q=sports&tbm=isch&ved=2ahUKEwjZh6eigZT6AhUTRhoKHZidCdMQ2-cCegQIABAA&oq=sports&gs_lcp=CgNpbWcQA1DnEljUIWCCJGgAcAB4AIABAIgBAJIBAJgBAKABAaoBC2d3cy13aXotaW1nsAEAwAEB&sclient=img&ei=PKQhY9n5FJOMaZi7ppgN&bih=625&biw=1366#imgrc=NgM5b41zuhw5AM"));
        categoryModelArrayList.add(new CategoryModel("General","https://www.google.com/search?q=general+news&tbm=isch&ved=2ahUKEwiU3Ke8gZT6AhUIg84BHfJdCLkQ2-cCegQIABAA&oq=general+news&gs_lcp=CgNpbWcQA1CCBFjTF2DUG2gAcAB4AIABAIgBAJIBAJgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=cqQhY5TbNYiGur4P8ruhyAs&bih=625&biw=1366#imgrc=SgnUUstRHBdIHM"));
        categoryModelArrayList.add(new CategoryModel("Health","https://www.google.com/search?q=health&tbm=isch&ved=2ahUKEwiOwLi_gZT6AhUThXMKHV3_COYQ2-cCegQIABAA&oq=health&gs_lcp=CgNpbWcQA1CXBli0GGC1G2gAcAB4AIABAIgBAJIBAJgBAKABAaoBC2d3cy13aXotaW1nsAEAwAEB&sclient=img&ei=eaQhY86fG5OKzgPd_qOwDg&bih=625&biw=1366#imgrc=TYEm17LFEgDKkM"));
        categoryAdapter.notifyDataSetChanged();
    }
    private void getNews(String category){
        articlesArrayList.clear();
        String categoryUrl="https://newsapi.org/v2/top-headlines/sources?category="+category+"&apiKey=aa0af3af8bbb4442a1bada910b60195b";
        String allUrl="http://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=aa0af3af8bbb4442a1bada910b60195b";
        String BASE_URL="http://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<NewsModel> call;
        if(category.equals("All")){
            call=api.getAllNews(allUrl);
        }
        else {
            call=api.getNewsCategory(categoryUrl);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel= response.body();
                ArrayList<Articles> articles=newsModel.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrl(),articles.get(i).getUrlToImage()));
                    newsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Somrthing went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
   String category=categoryModelArrayList.get(position).getCategory();
   getNews(category);
    }
}