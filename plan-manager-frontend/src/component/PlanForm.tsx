import { FormProvider, useForm } from "react-hook-form";
import { Prefectures, Status } from "redux/types";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import {
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  TextFieldProps,
} from "@mui/material";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import DatePicker from "@mui/lab/DatePicker";
import { useEffect, useState } from "react";
export const schema = yup.object().shape({
  title: yup.string().required("入力は必須です。"),
  description: yup.string().required("入力は必須です。"),
  prefecture: yup.mixed().oneOf(Prefectures.concat([])),
  start_date: yup.string().required("入力は必須です。"),
  end_date: yup.string().required("入力は必須です。"),
  status: yup.mixed().oneOf(Status.concat([])),
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
  id,
  userId,
  title = "",
  description = "",
  prefecture = "",
  start_date = "",
  end_date = "",
  status = "",
  type,
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
  const onSubmit = (data: PlanModel) => console.log(data);
  const [submitting, setSubmitting] = useState(false);
  return (
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
        margin="normal"
        error={!!errors.description}
        helperText={errors.description?.message}
        {...register("description")}
      />
      {/* TODO: ここ表示されてない */}
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <DatePicker
          label="開始日"
          inputFormat="yyyy/MM/dd"
          value={null}
          onChange={null}
          renderInput={(params: JSX.IntrinsicAttributes & TextFieldProps) => (
            <TextField
              {...params}
              fullWidth
              margin="normal"
              error={!!errors.start_date}
              helperText={errors.start_date?.message}
            />
          )}
        />
      </LocalizationProvider>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <DatePicker
          label="終了日"
          inputFormat="yyyy/MM/dd"
          value={null}
          onChange={null}
          renderInput={(params: JSX.IntrinsicAttributes & TextFieldProps) => (
            <TextField
              {...params}
              fullWidth
              margin="normal"
              error={!!errors.start_date}
              helperText={errors.start_date?.message}
            />
          )}
        />
      </LocalizationProvider>
      <FormControl fullWidth margin="normal" error={!!errors.prefecture}>
        <InputLabel>場所</InputLabel>
        <Select {...register("prefecture")} label="場所">
          {Prefectures.map((prefecture) => (
            <MenuItem key={prefecture} value={prefecture}>
              {prefecture}
            </MenuItem>
          ))}
        </Select>
        {errors.prefecture && <div>{errors.prefecture.message}</div>}
      </FormControl>
      <FormControl fullWidth margin="normal" error={!!errors.status}>
        <InputLabel>ステータス</InputLabel>
        <Select {...register("prefecture")} label="場所">
          {Status.map((status) => (
            <MenuItem key={status} value={status}>
              {status}
            </MenuItem>
          ))}
        </Select>
        {errors.status && <div>{errors.status.message}</div>}
      </FormControl>
      <button type="submit" disabled={submitting}>
        送信
      </button>
    </form>
  );
};
