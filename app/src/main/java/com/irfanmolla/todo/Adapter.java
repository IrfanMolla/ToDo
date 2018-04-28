package com.irfanmolla.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/11/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder>{

    private Context mctx;
    ArrayList<List_Items> itemsList=new ArrayList<>();

    public Adapter(Context context, ArrayList<List_Items> itemsList) {
        this.mctx=context;
        this.itemsList = itemsList;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.cardview,parent,false);
        viewholder viewholder=new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        String  shortTitle;
        final List_Items list=itemsList.get(position);

        holder.id.setText(list.getId());
        if ((list.getTitle()).length()>29){
            shortTitle=list.getTitle();
            holder.title.setText(shortTitle.substring(0,29)+"...");
        }
        else {
            holder.title.setText(list.getTitle());
        }
        String MonthName = "abcdef";
        switch (Integer.parseInt(list.getMonth())){
            case 1:
                MonthName="Jan";
                break;
            case 2:
                MonthName="Feb";
                break;
            case 3:
                MonthName="Mar";
                break;
            case 4:
                MonthName="Apr";
                break;
            case 5:
                MonthName="May";
                break;
            case 6:
                MonthName="Jun";
                break;
            case 7:
                MonthName="Jul";
                break;
            case 8:
                MonthName="Aug";
                break;
            case 9:
                MonthName="Sep";
                break;
            case 10:
                MonthName="Oct";
                break;
            case 11:
                MonthName="Nov";
                break;
            case 12:
                MonthName="Dec";
                break;
        }
        String dayname="abcdef";
        switch (Integer.parseInt(list.getDayname())){
            case 1:
                dayname="Mon";
                break;
            case 2:
                dayname="Tue";
                break;
            case 3:
                dayname="Wed";
                break;
            case 4:
                dayname="Thu";
                break;
            case 5:
                dayname="Fri";
                break;
            case 6:
                dayname="Sat";
                break;
            case 7:
                dayname="Sun";
                break;

        }

        holder.date.setText(dayname+ " "+ list.getDay()+ "th "+ MonthName+ ", "+(Integer.parseInt(list.getYear()))%100 );
        holder.time.setText(list.getHour()+ " : " + list.getMinute());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(v.getContext(),NewItem.class);
                i.putExtra("id",list.getId());
                i.putExtra("title",list.getTitle());
                i.putExtra("dayname",list.getDayname());
                i.putExtra("day",list.getDay());
                i.putExtra("month",list.getMonth());
                i.putExtra("year",list.getYear());
                i.putExtra("hour",list.getHour());
                i.putExtra("minute",list.getMinute());
                i.putExtra("note",list.getNote());
                i.putExtra("pass_code",101);
                v.getContext().startActivity(i);

            }

        });

    }

    @Override
    public int getItemCount() {

        return itemsList.size() ;
    }

    public static class viewholder extends RecyclerView.ViewHolder{

        TextView id,title,date,time;
        ArrayList<List_Items> list_items= new ArrayList<List_Items>();
        Context mctx;
        public viewholder(View itemView) {
            super(itemView);



            id=itemView.findViewById(R.id.cardID);
            title=itemView.findViewById(R.id.kaaj);
            date=itemView.findViewById(R.id.tarikh);
            time=itemView.findViewById(R.id.somoy);



        }
    }
}
