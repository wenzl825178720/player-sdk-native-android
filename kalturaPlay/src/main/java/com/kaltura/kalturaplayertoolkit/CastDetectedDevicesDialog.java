package com.kaltura.kalturaplayertoolkit;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.example.kplayersdk.R;
import com.kaltura.playersdk.casting.KRouterInfo;

import java.util.ArrayList;

/**
 * Created by nissimpardo on 10/11/15.
 */
public class CastDetectedDevicesDialog extends Dialog {
    private ArrayList<KRouterInfo> mRouteNames;
    private ArrayAdapter<KRouterInfo> mAdapter;

    public interface CastDetectedDevicesDialogListener {
        public void disconnect();
        public void routeSelected(String castDeviceId);
    }

    public CastDetectedDevicesDialog(Context context, final CastDetectedDevicesDialogListener listener) {
        super(context);
        setContentView(R.layout.media_routers);
        setTitle(Html.fromHtml("<font color='#0db6d1'>Connect to Device</font>"));
        mRouteNames = new ArrayList<KRouterInfo>();
        final ListView routeListView = (ListView)findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<KRouterInfo>(context, android.R.layout.simple_list_item_1, mRouteNames);
        routeListView.setAdapter(mAdapter);
        routeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.routeSelected(mRouteNames.get(position).getRouterId());
                dismiss();
            }
        });

    }



    @Override
    public void show() {
        super.show();
        mAdapter.notifyDataSetChanged();
    }

    public void addCastDevice(KRouterInfo info) {
        mRouteNames.add(info);
        if (isShowing()) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void removeCastDevice(KRouterInfo info) {
        mRouteNames.remove(info);
        if (isShowing()) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
