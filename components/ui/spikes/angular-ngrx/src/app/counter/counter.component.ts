import {Component} from "@angular/core";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {decrement, increment, reset } from "../ngrx/counter.actions";

@Component({
  selector: 'app-my-counter',
  templateUrl: './counter.component.html'
})
export class CounterComponent {
  count$: Observable<number>

  constructor(private store: Store<{count: number}>) {
    this.count$ = store.select('count');
  }

  increment() {
    this.store.dispatch(increment())
  }

  decrement() {
    this.store.dispatch(decrement())
  }

  reset() {
    this.store.dispatch(reset())
  }
}
