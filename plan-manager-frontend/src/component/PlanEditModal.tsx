import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { useEffect } from "react";
import { Plan } from "redux/types";
import { PlanForm } from "./PlanForm";

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
};

export interface PlanEditModalProps {
  isOpen: boolean;
  onClose: () => void;
  plan: Plan;
}

export const PlanEditModal = ({
  isOpen,
  plan,
  onClose,
}: PlanEditModalProps) => {
  return (
    <Modal
      open={isOpen}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <PlanForm type="edit" />
      </Box>
    </Modal>
  );
};
