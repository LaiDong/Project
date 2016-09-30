package com.android_examples.ld.tracnghiem;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android_examples.ld.R;

import java.util.ArrayList;
import java.util.List;

public class TracNghiem_Activity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tracnghiem, container, false);

        ListView list = (ListView) view.findViewById(R.id.lstTracNghiem);
        ItemTracNghiem item1 = new ItemTracNghiem("Trắc nghiệm kỹ năng phỏng vấn xin việc", "10", "8");
        ItemTracNghiem item2 = new ItemTracNghiem("Trắc nghiệm sự tự tin của bạn", "10", "5");
        ItemTracNghiem item3 = new ItemTracNghiem("Trắc nghiệm về kinh nghiệm phỏng vấn cho nhân viên IT", "10", "5");
        ItemTracNghiem item4 = new ItemTracNghiem("Trắc nghiệm kỹ năng phỏng vấn xin việc", "10", "5");
        ItemTracNghiem item5 = new ItemTracNghiem("Trắc nghiệm kỹ năng phỏng vấn xin việc", "10", "5");
        ItemTracNghiem item6 = new ItemTracNghiem("Trắc nghiệm kỹ năng phỏng vấn xin việc", "10", "5");
        ItemTracNghiem item7 = new ItemTracNghiem("Trắc nghiệm kỹ năng phỏng vấn xin việc", "10", "5");
        ItemTracNghiem item8 = new ItemTracNghiem("Trắc nghiệm kỹ năng phỏng vấn xin việc", "10", "5");

        List<ItemTracNghiem> tracNghiemList = new ArrayList<>();
        tracNghiemList.add(item1);
        tracNghiemList.add(item2);
        tracNghiemList.add(item3);
        tracNghiemList.add(item4);
        tracNghiemList.add(item5);
        tracNghiemList.add(item6);
        tracNghiemList.add(item7);
        tracNghiemList.add(item8);

        AdapterTN adapterTN = new AdapterTN(tracNghiemList, getActivity());
        list.setAdapter(adapterTN);

        return view;

    }


}
