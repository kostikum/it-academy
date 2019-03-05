package com.kostikum.itac.dz4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kostikum.itac.R;

public class PieChartFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        v.findViewById(R.id.pie_chart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] stringNumbers = ((EditText) v.findViewById(R.id.pie_chart_edit_text))
                        .getText().toString().split(",");

                int[] numbers = new int[stringNumbers.length];

                for (int i = 0; i < stringNumbers.length; i++) {
                    try {
                        numbers[i] = Integer.parseInt(stringNumbers[i]);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity(), "Incorrect input", Toast.LENGTH_LONG).show();
                    }
                }

                ((PieChartView) v.findViewById(R.id.pie_chart_view)).recutPieChart(numbers);
            }
        });
        return v;
    }
}
