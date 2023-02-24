import dayjs from "dayjs";

export const getDateString = (date: Date) => {
  return dayjs(date).format("YYYY/MM/DD HH:mm");
};
