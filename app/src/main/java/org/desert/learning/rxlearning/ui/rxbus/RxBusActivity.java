package org.desert.learning.rxlearning.ui.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.desert.learning.rxlearning.R;
import org.desert.learning.rxlearning.model.Event;
import org.desert.learning.rxlearning.ui.rxbus.RxBus;

public class RxBusActivity extends AppCompatActivity {

  @BindView(R.id.text) TextView mText;
  @BindView(R.id.btn) Button mBtn;
  private final CompositeDisposable mDisposable = new CompositeDisposable();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rx_bus);
    ButterKnife.bind(this);

    mDisposable.add(RxBus.getInstance().sendAutoEvent());

    mDisposable.add(RxBus.getInstance()
        .toObserverable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(event -> event.toString())
        .subscribe(RxTextView.text(mText)));

    Disposable subscribe =
        RxView.clicks(mBtn).subscribe(o -> RxBus.getInstance().send(Event.TabEvent));
    mDisposable.add(subscribe);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mDisposable.clear();
  }
}
