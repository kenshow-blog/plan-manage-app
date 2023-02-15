import {
  createAsyncThunk,
  createSelector,
  createSlice,
} from "@reduxjs/toolkit";
import { getPlanListApi } from "services/planService";
import { RootState } from "./store";

export interface User {
  id: number;
  name: string;
}
export interface Whether {
  dt: string;
  tem: {
    day: number;
    min: number;
    max: number;
    night: number;
    eve: number;
    morn: number;
  };
  sunrise: string;
  sunset: string;
  whether: string;
}
export interface Plan {
  id: number;
  user: User;
  title: string;
  description: string;
  prefecture: string;
  start_date: string;
  end_date: string;
  status: string;
  whether?: Whether;
}
export interface PlanState {
  plan_list: Plan[];
}

export const getPlanList = createAsyncThunk<Plan[]>("getPlanList", async () => {
  return await getPlanListApi();
});
const initialState: PlanState = {
  plan_list: [],
};
const planSlice = createSlice({
  initialState,
  name: "plan",
  reducers: {
    initData: (state) => {
      Object.assign(state, initialState);
    },
  },
  extraReducers: (builder) => {
    builder.addCase(getPlanList.fulfilled, (state, action) => {
      state.plan_list = action.payload;
    });
    builder.addCase(getPlanList.rejected, (state) => {
      state.plan_list = initialState.plan_list;
    });
  },
});

export const { initData } = planSlice.actions;

export const selectPlanList = (state: RootState) => state.plan;
export default planSlice;
