package org.desert.learning.rxlearning.ui.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import org.desert.learning.rxlearning.R;
import org.desert.learning.rxlearning.util.Global;

public class OperatorsActivity extends AppCompatActivity {

  final static String[] operators = Global.getResources().getStringArray(R.array.operators);

  @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_operators);
    ButterKnife.bind(this);
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    OperatorsAdapter adapter = new OperatorsAdapter(operators);
    mRecyclerView.setAdapter(adapter);

    mCompositeDisposable.add(adapter.getOnClickSubject().subscribe(s -> startXPage(s)));
  }

  private void startXPage(String s) {
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mCompositeDisposable.dispose();
    mCompositeDisposable.clear();
  }

  final static class OperatorsAdapter extends RecyclerView.Adapter<OperatorVH> {

    private final PublishSubject<String> onClickSubject = PublishSubject.create();

    String[] operators;

    public OperatorsAdapter(String[] operators) {
      this.operators = operators;
    }

    @Override public OperatorVH onCreateViewHolder(ViewGroup parent, int viewType) {
      return new OperatorVH(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_operator, parent, false));
    }

    @Override public void onBindViewHolder(OperatorVH holder, int position) {
      holder.mTextView.setText(operators[position]);
      RxView.clicks(holder.mTextView)
          .map(o -> operators[position])
          .subscribe(onClickSubject::onNext);
    }

    public PublishSubject<String> getOnClickSubject() {
      return onClickSubject;
    }

    @Override public int getItemCount() {
      return null == operators ? 0 : operators.length;
    }
  }

  private static class OperatorVH extends RecyclerView.ViewHolder {

    TextView mTextView;

    public OperatorVH(View itemView) {
      super(itemView);
      mTextView = (TextView) itemView;
    }
  }
}
