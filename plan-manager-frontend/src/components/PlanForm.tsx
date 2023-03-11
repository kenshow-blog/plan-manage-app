import { useForm } from "react-hook-form";
import { Prefectures, RequestBodyPlan, Status } from "../redux/types";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import {
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  FormHelperText,
  Button,
  Grid,
} from "@mui/material";
import { LocalizationProvider } from "@mui/x-date-pickers-pro";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import ja from "date-fns/locale/ja";
import { AppDispatch } from "../redux/store";
import { useDispatch, useSelector } from "react-redux";
import {
  createPlan,
  resetLoading,
  selectLoading,
  setLoading,
  updatePlan,
} from "../redux/planSlice";
import CircularProgress from "@mui/material/CircularProgress";
import { useCallback } from "react";

export const schema = yup.object().shape({
  title: yup.string().required("入力は必須です。"),
  description: yup.string().required("入力は必須です。"),
  prefecture: yup
    .mixed()
    .oneOf(Prefectures.concat([]), "都道府県を選択してください。"),
  start_date: yup.string().required("入力は必須です。"),
  end_date: yup
    .string()
    .required("入力は必須です。")
    .test(
      "date test",
      "終了日時は開始日時より後に設定してください。",
      function (value) {
        const start_date = this.resolve(yup.ref("start_date")) as string;
        if (!value || !start_date) {
          return true;
        }
        return new Date(value) >= new Date(start_date);
      }
    ),
  status: yup
    .mixed()
    .oneOf(Status.concat([]), "ステータスを選択してください。"),
});

export type PlanModel = yup.InferType<typeof schema>;

export const PlanFormPropType = ["edit", "new"] as const;
interface PlanFormProps {
  id?: number;
  userId?: number;
  title?: string;
  description?: string;
  prefecture?: (typeof Prefectures)[number];
  start_date?: string;
  end_date?: string;
  status?: (typeof Status)[number];
  type: (typeof PlanFormPropType)[number];
  onClose: () => void;
}
const PlanForm = ({
  id = 0,
  title = "",
  description = "",
  prefecture = "東京都",
  start_date = undefined,
  end_date = undefined,
  status = "",
  type,
  onClose,
}: PlanFormProps) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<PlanModel>({
    mode: "onBlur",
    defaultValues: {
      title: title,
      description: description,
      prefecture: prefecture,
      start_date: start_date,
      end_date: end_date,
      status: status,
    },
    resolver: yupResolver(schema),
  });
  const dispatch: AppDispatch = useDispatch();
  const isLoading = useSelector(selectLoading);
  const onSubmit = useCallback(
    async (data: PlanModel) => {
      dispatch(setLoading());
      const params: RequestBodyPlan = {
        user_id: 2,
        title: data.title,
        description: data.description,
        prefecture: data.prefecture as string,
        start_date: data.start_date,
        end_date: data.end_date,
        status: data.status as string,
      };
      if (type === "edit") {
        params["id"] = id;
        await dispatch(updatePlan(params));
      } else {
        await dispatch(createPlan(params));
      }
      onClose();
      dispatch(resetLoading());
    },
    [dispatch, onClose]
  );
  return (
    <>
      {isLoading ? (
        <CircularProgress />
      ) : (
        <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={ja}>
          <form onSubmit={handleSubmit(onSubmit)}>
            <Grid container spacing={2}>
              <Grid xs={12} sm={8} p={4}>
                <TextField
                  label="タイトル"
                  fullWidth
                  margin="normal"
                  error={!!errors.title}
                  helperText={errors.title?.message}
                  {...register("title")}
                />
                <TextField
                  label="内容"
                  fullWidth
                  multiline
                  rows={4}
                  margin="normal"
                  error={!!errors.description}
                  helperText={errors.description?.message}
                  {...register("description")}
                />
              </Grid>
              <Grid xs={12} sm={4} p={4}>
                <TextField
                  label="開始日時"
                  type="datetime-local"
                  sx={{ width: 250, mb: 2 }}
                  error={!!errors.start_date}
                  helperText={errors.start_date?.message}
                  {...register("start_date")}
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
                <TextField
                  label="終了日時"
                  type="datetime-local"
                  sx={{ width: 250 }}
                  error={!!errors.end_date}
                  helperText={errors.end_date?.message}
                  {...register("end_date")}
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
                <FormControl
                  fullWidth
                  margin="normal"
                  error={!!errors.prefecture}
                  sx={{ width: 250 }}
                >
                  <InputLabel>場所</InputLabel>
                  <Select
                    defaultValue={prefecture}
                    {...register("prefecture")}
                    label="場所"
                    sx={{ textAlign: "left" }}
                  >
                    {Prefectures.map((prefecture) => (
                      <MenuItem key={prefecture} value={prefecture}>
                        {prefecture}
                      </MenuItem>
                    ))}
                  </Select>
                  {errors.prefecture && (
                    <FormHelperText>{errors.prefecture.message}</FormHelperText>
                  )}
                </FormControl>
                <FormControl
                  fullWidth
                  margin="normal"
                  error={!!errors.status}
                  sx={{ width: 250 }}
                >
                  <InputLabel>ステータス</InputLabel>
                  <Select
                    defaultValue={status}
                    {...register("status")}
                    label="場所"
                    sx={{ textAlign: "left" }}
                  >
                    {Status.map((status) => (
                      <MenuItem key={status} value={status}>
                        {status}
                      </MenuItem>
                    ))}
                  </Select>
                  {errors.status && (
                    <FormHelperText>{errors.status.message}</FormHelperText>
                  )}
                </FormControl>
              </Grid>
            </Grid>
            <Grid container spacing={2}>
              <Grid xs={6}>
                <Button variant="outlined" color="primary" onClick={onClose}>
                  戻る
                </Button>
              </Grid>
              <Grid xs={6}>
                <Button type="submit" variant="contained" color="primary">
                  {type === "edit" ? "更新" : "新規作成"}
                </Button>
              </Grid>
            </Grid>
          </form>
        </LocalizationProvider>
      )}
    </>
  );
};

export default PlanForm;
