import axios from "axios";
import { Plan, PlanId, RequestBodyPlan } from "../redux/types";
import { getPostCache } from "../util/chache";

// const basicApi = axios.create({
//   baseURL: process.env.BASE_URL,
//   headers: { "Content-Type": "application/json" },
// });
export const getPlanListApi = async (): Promise<Plan[]> => {
  const cache = getPostCache();
  if (cache) return cache;
  const res = await axios.get(`${process.env.NEXT_PUBLIC_BASE_URL}/plan/list`);
  return res.data;
};

export const createPlanApi = (requestBody: RequestBodyPlan): Promise<Plan> =>
  axios
    .post(`${process.env.NEXT_PUBLIC_BASE_URL}/plan/save`, requestBody)
    .then((res) => res.data);

export const updatePlanApi = (requestBody: RequestBodyPlan): Promise<Plan> =>
  axios
    .put(`${process.env.NEXT_PUBLIC_BASE_URL}/plan/update`, requestBody)
    .then((res) => res.data);

export const deletePlanApi = (queryParam: number): Promise<PlanId> =>
  axios
    .delete(`${process.env.NEXT_PUBLIC_BASE_URL}/plan/delete/${queryParam}`)
    .then((res) => res.data);
