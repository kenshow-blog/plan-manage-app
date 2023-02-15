import { Action, configureStore, ThunkAction } from "@reduxjs/toolkit";
import planSlice from "./planSlice";

const store = configureStore({
  reducer: {
    plan: planSlice.reducer,
  },
});
export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;

export type AppDispatch = typeof store.dispatch;
