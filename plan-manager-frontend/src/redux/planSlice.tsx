import {
  createAsyncThunk,
  createSelector,
  createSlice,
} from "@reduxjs/toolkit";
import {
  createPlanApi,
  deletePlanApi,
  getPlanListApi,
  updatePlanApi,
} from "services/planService";
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

export interface PlanId {
  id: number;
}
export interface PlanState {
  plan_list: Plan[];
}
export interface CreatePlanPamras {
  user_id: number;
  title: string;
  description: string;
  prefecture: string;
  start_date: string;
  end_date: string;
  status: string;
}
export interface UpdatePlanParams {
  id: number;
  user_id: number;
  title: string;
  description: string;
  prefecture: string;
  start_date: string;
  end_date: string;
  status: string;
}

export const getPlanList = createAsyncThunk<Plan[]>("getPlanList", async () => {
  return await getPlanListApi();
});

export const createPlan = createAsyncThunk(
  "createPlan",
  async (params: CreatePlanPamras) => {
    return await createPlanApi(params);
  }
);

export const updatePlan = createAsyncThunk(
  "updatePlan",
  async (params: UpdatePlanParams) => {
    return await updatePlanApi(params);
  }
);

export const deletePlan = createAsyncThunk("deletePlan", async (id: number) => {
  return await deletePlanApi(id);
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
    builder.addCase(createPlan.fulfilled, (state, action) => {
      state.plan_list.push(action.payload);
    });
    builder.addCase(createPlan.rejected, () => {
      alert("予定の作成に失敗しました。");
    });
    builder.addCase(updatePlan.fulfilled, (state, action) => {
      state.plan_list = state.plan_list.map((p) =>
        p.id === action.payload.id ? action.payload : p
      );
    });
    builder.addCase(updatePlan.rejected, () => {
      alert("予定の更新に失敗しました。");
    });
    builder.addCase(deletePlan.fulfilled, (state, action) => {
      state.plan_list = state.plan_list.filter(
        (p) => p.id !== action.payload.id
      );
    });
    builder.addCase(deletePlan.rejected, () => {
      alert("予定の削除に失敗しました。");
    });
  },
});

export const { initData } = planSlice.actions;

export const selectPlanList = (state: RootState) => state.plan;
export default planSlice;
