import axios from "axios";
import { Plan } from "redux/planSlice";

const basicApi = axios.create({
  baseURL: "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
});
export const getPlanListApi = (): Promise<Plan[]> =>
  basicApi.get("plan/list").then((res) => res.data);
