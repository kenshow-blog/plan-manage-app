import * as React from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import { PlanForm } from "./PlanForm";

const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  textAlign: "center",
};

export interface CreatePlanModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const CreatePlanModal = ({ isOpen, onClose }: CreatePlanModalProps) => {
  return (
    <Modal
      open={isOpen}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <PlanForm type="new" onClose={onClose} />
      </Box>
    </Modal>
  );
};
