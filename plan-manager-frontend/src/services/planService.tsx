import axios from "axios";
import { Plan, PlanId, RequestBodyPlan } from "redux/types";
import { getPostCache } from "util/chache";

const basicApi = axios.create({
  baseURL: "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
});
export const getPlanListApi = async (): Promise<Plan[]> => {
  const cache = getPostCache();
  if (cache) return cache;
  const res = await basicApi.get("plan/list");
  return res.data;
};

export const createPlanApi = (requestBody: RequestBodyPlan): Promise<Plan> =>
  basicApi.post("plan/save", requestBody).then((res) => res.data);

export const updatePlanApi = (requestBody: RequestBodyPlan): Promise<Plan> =>
  basicApi.put("plan/update", requestBody).then((res) => res.data);

export const deletePlanApi = (queryParam: number): Promise<PlanId> =>
  basicApi.delete(`plan/delete/${queryParam}`).then((res) => res.data);
