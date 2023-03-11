import { Box, Typography, Button } from "@mui/material";
import AppHead from "../components/Head";
import Image from "next/image";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getPlanList, selectPlanList } from "../redux/planSlice";
import { AppDispatch } from "../redux/store";
import styles from "../styles/Home.module.css";
import CreatePlanModal from "../components/CreatePlanModal";
import CollapsibleTable from "../components/PlanCollapsibleTable";

export default function Home() {
  const [openCreateModal, setCreateModal] = useState(false);
  const handleCreateModalOpen = () => setCreateModal(true);
  const handleCreateModalClose = () => setCreateModal(false);
  const dispatch: AppDispatch = useDispatch();
  const selectedPlanList = useSelector(selectPlanList);
  useEffect(() => {
    dispatch(getPlanList());
  }, []);
  return (
    <Box className={styles.container}>
      <AppHead />
      <Box component="section" mb={8} mt={4}>
        <Typography variant="h1" fontSize={24} fontWeight={600} mt={4} mb={6}>
          予定管理アプリ
        </Typography>
        <Box>
          <Typography variant="body1" mt={2} mb={4}>
            <b>説明:</b>
            <br />
            予定を管理するアプリです。
            <br />
            8日以内の予定であればその日の天気情報を確認することができます。
          </Typography>
        </Box>
        <Button
          variant="contained"
          color="primary"
          onClick={handleCreateModalOpen}
        >
          新規作成
        </Button>
      </Box>
      <CreatePlanModal
        isOpen={openCreateModal}
        onClose={handleCreateModalClose}
      />
      <CollapsibleTable planList={selectedPlanList} />
      <footer className={styles.footer}>
        <a
          href="https://vercel.com?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          Powered by{" "}
          <span className={styles.logo}>
            <Image src="/vercel.svg" alt="Vercel Logo" width={72} height={16} />
          </span>
        </a>
      </footer>
    </Box>
  );
}
