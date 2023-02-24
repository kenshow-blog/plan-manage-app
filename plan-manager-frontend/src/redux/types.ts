export interface RequestBodyPlan {
  id?: number;
  user_id: number;
  title: string;
  description: string;
  prefecture: string;
  start_date: string;
  end_date: string;
  status: string;
}
export interface User {
  id: number;
  name: string;
}
export interface Whether {
  dt: string;
  tem: {
    day: number;
    min: number;
    max: number;
    night: number;
    eve: number;
    morn: number;
  };
  sunrise: string;
  sunset: string;
  icon: string;
  whether: string;
}
export interface Plan {
  id: number;
  user: User;
  title: string;
  description: string;
  prefecture: (typeof Prefectures)[number];
  start_date: string;
  end_date: string;
  status: (typeof Status)[number];
  whether?: Whether;
}

export interface PlanId {
  id: number;
}
export interface PlanState {
  isLoading: boolean;
  plan_list: Plan[];
}
export const Status = ["", "COMPLETE", "PROCESS", "UNPROCESSED"] as const;
export const Prefectures = [
  "",
  "東京都",
  "神奈川県",
  "埼玉県",
  "千葉県",
  "茨城県",
  "群馬県",
  "栃木県",
  "大阪府",
  "兵庫県",
  "京都府",
  "滋賀県",
  "奈良県",
  "和歌山県",
  "福岡県",
  "熊本県",
  "長崎県",
  "大分県",
  "鹿児島県",
  "宮崎県",
  "佐賀県",
  "愛知県",
  "北海道",
  "広島県",
  "岡山県",
  "沖縄県",
  "神戸県",
  "福井県",
  "静岡県",
  "岐阜県",
  "石川県",
  "富山県",
  "愛媛県",
  "香川県",
  "高知県",
  "山口県",
  "徳島県",
  "岩手県",
  "山形県",
  "山梨県",
  "秋田県",
  "宮城県",
  "福島県",
  "新潟県",
  "長野県",
] as const;
