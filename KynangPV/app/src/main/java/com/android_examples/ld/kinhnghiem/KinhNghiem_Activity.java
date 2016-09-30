package com.android_examples.ld.kinhnghiem;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android_examples.ld.R;
import com.android_examples.ld.tracnghiem.AdapterTN;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class KinhNghiem_Activity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tracnghiem, container, false);
        new GetImage().execute();

        ListView list = (ListView) view.findViewById(R.id.lstTracNghiem);
        ItemKinhNghiem itemKinhNghiem = new ItemKinhNghiem("Kinh nghiệm pv lập trình", "Tự tin, bình tĩnh làm tốt mọi thứ", R.drawable.sentence);
        ItemKinhNghiem itemKinhNghiem1 = new ItemKinhNghiem("Kinh nghiệm pv sản xuất", "Tự tin, bình tĩnh làm tốt mọi thứ", R.drawable.interview_icon);
        List<ItemKinhNghiem> lstItemKinhNghiem = new ArrayList<>();
        lstItemKinhNghiem.add(itemKinhNghiem);
        lstItemKinhNghiem.add(itemKinhNghiem1);
        AdapterKN adapterKN = new AdapterKN(lstItemKinhNghiem, getActivity());
        list.setAdapter(adapterKN);

        return view;
    }

    public class GetImage extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.e("Getnews", "on get data");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String link = "http://www.wn.com.vn/categories/Cong-nghe/Tin-tuc-cong-nghe/";
                Document doc = Jsoup.connect(link).get();
                Elements elements = doc.select("div.col-sm-7 a");
                for(int i =0; i < elements.size(); i ++){
                    Element e = elements.get(i);
                    Elements img = e.select("img");
                    String title = e.attr("title");
                    String image = img.attr("src");
                    Log.e("tuhoc", title + " " + image);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("tuhoc", "finish");

        }
    }
}
