import * as React from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import { Plan } from "redux/types";
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

export interface EditPlanModalProps {
  isOpen: boolean;
  onClose: () => void;
  plan: Plan;
}

export const EditPlanModal = ({
  isOpen,
  plan,
  onClose,
}: EditPlanModalProps) => {
  return (
    <Modal
      open={isOpen}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <PlanForm
          id={plan.id}
          title={plan.title}
          description={plan.description}
          prefecture={plan.prefecture}
          start_date={plan.start_date}
          end_date={plan.end_date}
          status={plan.status}
          type="edit"
          onClose={onClose}
        />
      </Box>
    </Modal>
  );
};
