package com.example.xdien.btnho;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.xdien.btnho.Adapter.LoaiHangHoaAdapter;
import com.example.xdien.btnho.Adapter.LoaiHangHoaListAdapter;
import com.example.xdien.btnho.SQL.LoaiHangHoaSQLHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiHangHoaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiHangHoaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LoaiHangHoaListAdapter loaiHangHoaListAdapter;
    ListView loaihhListView;
    LoaiHangHoaSQLHelper loaiHangHoaSQLHelper;


    public LoaiHangHoaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoaiHangHoaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoaiHangHoaFragment newInstance(String param1, String param2) {
        LoaiHangHoaFragment fragment = new LoaiHangHoaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loaiHangHoaSQLHelper  = new LoaiHangHoaSQLHelper(getContext());
        loaiHangHoaListAdapter = new LoaiHangHoaListAdapter(getContext(),loaiHangHoaSQLHelper.dsLoaiHangHangs());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_hang_hoa, container, false);
        loaihhListView = (ListView) view.findViewById(R.id.loaihhlistView);
        loaihhListView.setAdapter(loaiHangHoaListAdapter);
        return view;
    }

}
