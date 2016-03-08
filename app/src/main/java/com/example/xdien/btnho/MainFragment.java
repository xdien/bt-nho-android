package com.example.xdien.btnho;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.xdien.btnho.Adapter.HangHoaAdapter;
import com.example.xdien.btnho.Adapter.LoaiHangHoaAdapter;
import com.example.xdien.btnho.SQL.HangHoaSqlHelper;
import com.example.xdien.btnho.SQL.LoaiHangHoaSQLHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView mainListView;
    HangHoaAdapter hangHoaAdapter;
    HangHoaSqlHelper hangHoaSqlHelper;
    LoaiHangHoaAdapter loaiHangHoaAdapter;
    Spinner spinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hangHoaSqlHelper = new HangHoaSqlHelper(getContext());
        hangHoaAdapter = new HangHoaAdapter(getContext(),hangHoaSqlHelper.dsHangHoas());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mainListView = (ListView) view.findViewById(R.id.main_list);
        mainListView.setAdapter(hangHoaAdapter);
        registerForContextMenu(mainListView);
        return view;
    }
    //context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.ctx_mod_hang_hoa, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(itemid == R.id.xoaMN){
            hangHoaSqlHelper.xoa(hangHoaAdapter.getItemId(info.position));
            //refresh
            hangHoaAdapter = null;
            hangHoaAdapter = new HangHoaAdapter(getContext(),hangHoaSqlHelper.dsHangHoas());
            mainListView.setAdapter(hangHoaAdapter);
        }
        if(itemid == R.id.suaMN){

        }
        return super.onContextItemSelected(item);

    }
}
