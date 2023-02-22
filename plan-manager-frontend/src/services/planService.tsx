import axios from "axios";
import { Plan, PlanId, RequestBodyPlan } from "redux/types";

const basicApi = axios.create({
  baseURL: "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
});
const timestampOfDay = 86400000;
export const getPlanListApi = async (): Promise<Plan[]> => {
  const lastGetPlanListDate = localStorage.getItem("getPlanListDate");
  const lastPlanList = localStorage.getItem("planList");
  if (lastGetPlanListDate && lastPlanList) {
    console.log(
      (Date.parse(new Date().toString()) - Date.parse(lastGetPlanListDate)) /
        timestampOfDay
    );
    if (
      (Date.parse(new Date().toString()) - Date.parse(lastGetPlanListDate)) /
        timestampOfDay <
      1
    )
      return JSON.parse(lastPlanList) as Plan[];
  }
  const res = await basicApi.get("plan/list");
  localStorage.setItem("getPlanListDate", new Date().toString());
  localStorage.setItem("planList", JSON.stringify(res.data));
  return res.data;
};

export const createPlanApi = (requestBody: RequestBodyPlan): Promise<Plan> =>
  basicApi.post("plan/create", requestBody);

export const updatePlanApi = (requestBody: RequestBodyPlan): Promise<Plan> =>
  basicApi.put("plan/update", requestBody);

export const deletePlanApi = (queryParam: number): Promise<PlanId> =>
  basicApi.delete(`plan/delete/${queryParam}`);
