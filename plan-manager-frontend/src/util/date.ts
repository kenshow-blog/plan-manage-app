import dayjs from "dayjs";

export const getDateTimeString = (date: Date) => {
  return dayjs(date).format("YYYY/MM/DD HH:mm");
};

export const getDateString = (date: Date) => {
  return dayjs(date).format("YYYY/MM/DD");
};
