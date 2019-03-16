package ru.startandroid.management.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ru.startandroid.management.Classes.New;
import ru.startandroid.management.R;
import ru.startandroid.management.activities.NewsInfo;

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {
    Context context;
    List<New> news;
    public NewsAdapter(Context context,List<New> list){
        this.news = list;
        this.context = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        NewsHolder pvh = new NewsHolder(context,v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        holder.setHolder(news.get(position).getTitle(),news.get(position).getBody(),news.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
class NewsHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView description;
    TextView date;
    View view;
    Context context;
    public NewsHolder(Context context, View itemView) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        title = (TextView)itemView.findViewById(R.id.news_title);
        description = (TextView)itemView.findViewById(R.id.news_description);
        date = (TextView) itemView.findViewById(R.id.news_date);
    }
    public void setHolder(final String t,final String s,final String d){
        title.setText(t);
        description.setText(s);
        date.setText(d);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NewsInfo.class);
                i.putExtra("title",t);
                i.putExtra("des",s);
                i.putExtra("date",d);
                context.startActivity(i);
            }
        });
    }
}
