import { Plan } from "redux/types";

const timestampOfDay = 86400000;
export const getPostCache = (): Plan[] | null => {
  const lastGetPlanListDate = localStorage.getItem("getPlanListDate");
  const lastPlanList = localStorage.getItem("planList");
  if (
    lastGetPlanListDate &&
    lastPlanList &&
    (Date.parse(new Date().toString()) - Date.parse(lastGetPlanListDate)) /
      timestampOfDay <
      1
  ) {
    return JSON.parse(lastPlanList) as Plan[];
  }
  return null;
};
export const createPostCache = (data: Plan[]) => {
  localStorage.setItem("getPlanListDate", new Date().toString());
  localStorage.setItem("planList", JSON.stringify(data));
};
