import { useForm } from "react-hook-form";
import { Prefectures, RequestBodyPlan, Status } from "redux/types";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import {
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  FormHelperText,
} from "@mui/material";
import { useState } from "react";
import { LocalizationProvider } from "@mui/x-date-pickers-pro";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import ja from "date-fns/locale/ja";
import { AppDispatch } from "redux/store";
import { useDispatch, useSelector } from "react-redux";
import { createPlan, updatePlan } from "redux/planSlice";

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
      (value) => {
        // console.log(this);
        if (!value) {
          return true;
        }
        return true;
        // console.log(value);
        // console.log(start_date);
        // return new Date(value) >= new Date(start_date);
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
  prefecture?: string;
  start_date?: string;
  end_date?: string;
  status?: string;
  type: (typeof PlanFormPropType)[number];
}
export const PlanForm = ({
  id = 0,
  userId,
  title = "",
  description = "",
  prefecture = "",
  start_date = undefined,
  end_date = undefined,
  status = "",
  type,
}: PlanFormProps) => {
  const {
    register,
    control,
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
  const onSubmit = async (data: PlanModel) => {
    setSubmitting(true);
    let params: RequestBodyPlan = {
      user_id: 2,
      title: data.title,
      description: data.description,
      prefecture: data.prefecture as string,
      start_date: data.start_date,
      end_date: data.end_date,
      status: data.status as string,
    };
    if (type === "edit") {
      params["id"] = 3;
      dispatch(updatePlan(params));
    } else {
      dispatch(createPlan(params));
    }
    setSubmitting(false);
  };
  const [submitting, setSubmitting] = useState(false);
  return (
    <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={ja}>
      <form onSubmit={handleSubmit(onSubmit)}>
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
          maxRows={5}
          margin="normal"
          error={!!errors.description}
          helperText={errors.description?.message}
          {...register("description")}
        />
        <TextField
          label="開始日時"
          type="datetime-local"
          sx={{ width: 250 }}
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
        {/* <Controller
          control={control}
          {...control.register("start_date")}
          render={({ field }) => (
            <DateTimePicker
              label="開始日時"
              inputFormat="yyyy/MM/dd HH:mm:ss"
              value={field.value}
              onChange={(newValue) => field.onChange(newValue)}
              //   {...register("start_date")}
              renderInput={(params) => (
                <TextField
                  {...params}
                  error={!!errors.start_date}
                  helperText={errors.start_date?.message}
                />
              )}
            />
          )}
        /> */}
        <FormControl fullWidth margin="normal" error={!!errors.prefecture}>
          <InputLabel>場所</InputLabel>
          <Select {...register("prefecture")} label="場所">
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
        <FormControl fullWidth margin="normal" error={!!errors.status}>
          <InputLabel>ステータス</InputLabel>
          <Select {...register("status")} label="場所">
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
        <button type="submit" disabled={submitting}>
          送信
        </button>
      </form>
    </LocalizationProvider>
  );
};
