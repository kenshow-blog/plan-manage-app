import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { useCallback } from "react";
import { AppDispatch } from "redux/store";
import { useDispatch, useSelector } from "react-redux";
import {
  deletePlan,
  resetLoading,
  selectLoading,
  setLoading,
} from "redux/planSlice";
import { CircularProgress } from "@mui/material";
const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  textAlign: "center",
};
export interface DeleteConfirmPlanModalProps {
  isOpen: boolean;
  onClose: () => void;
  planId: number;
}

export const DeleteConfirmPlanModal = ({
  isOpen,
  planId,
  onClose,
}: DeleteConfirmPlanModalProps) => {
  const dispatch: AppDispatch = useDispatch();
  const isLoading = useSelector(selectLoading);
  const handleDelete = useCallback(async () => {
    dispatch(setLoading());
    await dispatch(deletePlan(planId));
    onClose;
    dispatch(resetLoading());
  }, [dispatch, planId]);
  return (
    <Modal
      open={isOpen}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        {isLoading ? (
          <CircularProgress />
        ) : (
          <>
            <Typography id="modal-modal-description" sx={{ mt: 2, mb: 10 }}>
              本当に削除しますか？
            </Typography>
            <Box sx={{ display: "flex", justifyContent: "space-between" }}>
              <Button variant="outlined" color="primary" onClick={onClose}>
                戻る
              </Button>
              <Button
                variant="contained"
                color="inherit"
                onClick={handleDelete}
              >
                削除
              </Button>
            </Box>
          </>
        )}
      </Box>
    </Modal>
  );
};
