package mobileapplicationdevelopment.lynx.todo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RecycleViewAdapter extends BaseAdapter {

    private Context context;
    private List<GettersSetters> list;

    public RecycleViewAdapter(Context context, List<GettersSetters> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.activity_custom_list_items, null);
        ;

        TextView date = view.findViewById(R.id.txtDate);
        TextView description = view.findViewById(R.id.txtDescription);

        date.setText(list.get(position).getDate());
        description.setText(list.get(position).getDescription());
        view.setId(list.get(position).getId());

        if (new DateComparator(list.get(position).getDate()).oldToDo()) {
            view.setBackgroundColor(Color.RED);
            date.setTextColor(Color.WHITE);
            description.setTextColor(Color.WHITE);
        }

        return view;

    }
}
