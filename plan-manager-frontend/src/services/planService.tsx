import axios from "axios";
import { CreatePlanPamras, Plan, PlanId } from "redux/planSlice";

const basicApi = axios.create({
  baseURL: "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
});
export const getPlanListApi = (): Promise<Plan[]> =>
  basicApi.get("plan/list").then((res) => res.data);

export const createPlanApi = (requestBody: CreatePlanPamras): Promise<Plan> =>
  basicApi.post("plan/create", requestBody);

export const updatePlanApi = (requestBody: CreatePlanPamras): Promise<Plan> =>
  basicApi.put("plan/update", requestBody);

export const deletePlanApi = (queryParam: number): Promise<PlanId> =>
  basicApi.delete(`plan/delete/${queryParam}`);
