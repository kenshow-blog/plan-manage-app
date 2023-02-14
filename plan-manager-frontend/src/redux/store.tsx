import { configureStore } from "@reduxjs/toolkit";
import planSlice from "./planSlice";

const store = configureStore({
  reducer: {
    plan: planSlice.reducer,
  },
});
export default store;
