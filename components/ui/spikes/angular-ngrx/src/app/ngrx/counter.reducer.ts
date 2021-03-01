import {Action, ActionReducer, createReducer, on} from "@ngrx/store";
import {decrement, increment, reset} from "./counter.actions";

export const initialState: number = 0;
const _counterReducer = createReducer(
  initialState,
  on(increment, (state: number) => state + 1),
  on(decrement, (state: number) => state - 1),
  on(reset, (_) => 0)
);

export function counterReducer(state: number | undefined, action: Action) {
  return _counterReducer(state, action);
}
