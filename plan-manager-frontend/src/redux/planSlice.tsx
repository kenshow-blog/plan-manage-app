import { createSlice } from "@reduxjs/toolkit";

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
export interface PlanState {
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

const initialState: PlanState = {
  id: 0,
  user: {
    id: 0,
    name: "",
  },
  title: "",
  description: "",
  prefecture: "",
  start_date: "",
  end_date: "",
  status: "",
};
const planSlice = createSlice({
  initialState,
  name: "plan",
  reducers: {
    initData: (state) => {
      Object.assign(state, initialState);
    },
  },
});

export const { initData } = planSlice.actions;

export default planSlice;
