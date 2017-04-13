package org.desert.learning.rxlearning.ui.rxbus;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
import org.desert.learning.rxlearning.model.Event;

/**
 * Created by Desert on 31/03/2017.
 */

public class RxBus {

  private PublishSubject<Event> bus = PublishSubject.create();

  private RxBus() {
  }

  public static RxBus getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static final class InstanceHolder {
    private static final RxBus INSTANCE = new RxBus();
  }

  public void send(Event o) {
    bus.onNext(o);
  }

  public Observable<Event> toObserverable() {
    return bus;
  }

  public boolean hasObserver() {
    return bus.hasObservers();
  }

  public Disposable sendAutoEvent() {
    return Observable.interval(2, TimeUnit.SECONDS)
        .map(aLong -> Event.AutoEvent)
        .subscribe(event -> send(event));
  }
}
