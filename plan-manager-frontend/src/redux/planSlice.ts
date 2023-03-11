import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  createPlanApi,
  deletePlanApi,
  getPlanListApi,
  updatePlanApi,
} from "services/planService";
import { createPostCache } from "util/chache";
import { RootState } from "./store";
import { Plan, RequestBodyPlan, PlanState } from "./types";

export const getPlanList = createAsyncThunk<Plan[]>("getPlanList", async () => {
  return await getPlanListApi();
});

export const createPlan = createAsyncThunk(
  "createPlan",
  async (params: RequestBodyPlan) => {
    return await createPlanApi(params);
  }
);

export const updatePlan = createAsyncThunk(
  "updatePlan",
  async (params: RequestBodyPlan) => {
    return await updatePlanApi(params);
  }
);

export const deletePlan = createAsyncThunk("deletePlan", async (id: number) => {
  return await deletePlanApi(id);
});
const initialState: PlanState = {
  isLoading: false,
  plan_list: [],
};
const planSlice = createSlice({
  initialState,
  name: "plan",
  reducers: {
    initData: (state) => {
      Object.assign(state, initialState);
    },
    setLoading: (state) => {
      state.isLoading = true;
    },
    resetLoading: (state) => {
      state.isLoading = false;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(getPlanList.fulfilled, (state, action) => {
      state.plan_list = action.payload;
      createPostCache(state.plan_list);
    });
    builder.addCase(getPlanList.rejected, (state) => {
      state.plan_list = initialState.plan_list;
    });
    builder.addCase(createPlan.fulfilled, (state, action) => {
      state.plan_list.push(action.payload);
      createPostCache(state.plan_list);
    });
    builder.addCase(createPlan.rejected, () => {
      alert("予定の作成に失敗しました。");
    });
    builder.addCase(updatePlan.fulfilled, (state, action) => {
      state.plan_list = state.plan_list.map((p) =>
        p.id === action.payload.id ? action.payload : p
      );
      createPostCache(state.plan_list);
    });
    builder.addCase(updatePlan.rejected, () => {
      alert("予定の更新に失敗しました。");
    });
    builder.addCase(deletePlan.fulfilled, (state, action) => {
      state.plan_list = state.plan_list.filter(
        (p) => p.id !== action.payload.id
      );
      createPostCache(state.plan_list);
    });
    builder.addCase(deletePlan.rejected, () => {
      alert("予定の削除に失敗しました。");
    });
  },
});

export const { initData, setLoading, resetLoading } = planSlice.actions;

export const selectPlanList = (state: RootState): Plan[] => {
  const planList = [...state.plan.plan_list];
  return planList.sort(
    (a, b) => Date.parse(b.start_date) - Date.parse(a.start_date)
  );
};

export const selectLoading = (state: RootState) => state.plan.isLoading;
export default planSlice;
