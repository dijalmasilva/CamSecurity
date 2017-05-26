package br.com.dijalmasilva;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.dijalmasilva.pdm.forms.RecordVO;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 19/05/17.
 */

public class RecordListAdapter extends BaseAdapter {

    private final Activity activity;
    private final List<RecordVO> records;

    public RecordListAdapter(Activity activity, List<RecordVO> records) {
        this.activity = activity;
        this.records = records;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int i) {
        return records.get(i);
    }

    @Override
    public long getItemId(int i) {
//        return records.get(i).getId();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view_record = activity.getLayoutInflater().inflate(R.layout.layout_record, viewGroup, false);
        //
        final RecordVO recordVO = records.get(i);
        //
        ImageView imagePreview = (ImageView) view_record.findViewById(R.id.image_preview);
        TextView nameCam = (TextView) view_record.findViewById(R.id.recordCam);
        TextView dateRecord = (TextView) view_record.findViewById(R.id.recordDate);
        TextView hourRecord = (TextView) view_record.findViewById(R.id.recordHour);
        //
        imagePreview.setBackgroundResource(R.drawable.sample);
        nameCam.setText(recordVO.getNameWebCam());
        dateRecord.setText(recordVO.getData());
        hourRecord.setText(recordVO.getHour());
        //
        return view_record;
    }
}
