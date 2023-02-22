import * as React from "react";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import Image from "next/image";
import { useState } from "react";
import { PlanEditModal } from "./PlanEditModal";
import { PlanDeleteConfirmModal } from "./PlanDeleteConfirmModal";
import { Plan } from "redux/types";

const Row = (props: { row: Plan }) => {
  const { row } = props;
  const [open, setOpen] = useState(false);
  const [openEditModal, setEditModal] = useState(false);
  const handleEditModalOpen = () => setEditModal(true);
  const handleEditModalClose = () => setEditModal(false);
  const [openDeleteConfirmModal, setOpenDeleteConfirmModal] = useState(false);
  const handleDeleteConfirmModalOpen = () => setOpenDeleteConfirmModal(true);
  const handleDeleteConfirmModalClose = () => setOpenDeleteConfirmModal(false);
  console.log(row.description);
  return (
    <React.Fragment>
      <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
        <TableCell>
          {row.whether && (
            <IconButton
              aria-label="expand row"
              size="small"
              onClick={() => setOpen(!open)}
            >
              {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
            </IconButton>
          )}
        </TableCell>
        <TableCell component="th" scope="row">
          {row.title}
        </TableCell>
        <TableCell>{row.description}</TableCell>
        <TableCell>{row.prefecture}</TableCell>
        <TableCell>{row.start_date}</TableCell>
        <TableCell>{row.end_date}</TableCell>
        <TableCell>
          <IconButton
            aria-label="edit row"
            size="small"
            onClick={handleEditModalOpen}
          >
            <EditIcon />
          </IconButton>
          <IconButton
            aria-label="delete row"
            size="small"
            onClick={handleDeleteConfirmModalOpen}
          >
            <DeleteForeverIcon />
          </IconButton>
        </TableCell>
      </TableRow>
      {row.whether && (
        <TableRow>
          <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={10}>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <Box sx={{ margin: 1 }}>
                <Typography variant="h6" gutterBottom component="div">
                  天気情報
                </Typography>
                <Table size="small" aria-label="purchases">
                  <TableHead>
                    <TableRow>
                      <TableCell>日付</TableCell>
                      <TableCell>天気</TableCell>
                      <TableCell align="right">最高気温</TableCell>
                      <TableCell align="right">最低気温</TableCell>
                      <TableCell align="right">午前</TableCell>
                      <TableCell align="right">午後</TableCell>
                      <TableCell align="right">夜</TableCell>
                      <TableCell>日入時刻</TableCell>
                      <TableCell>日暮時刻</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    <TableRow key={row.whether.dt}>
                      <TableCell component="th" scope="row">
                        {row.whether.dt}
                      </TableCell>
                      <TableCell>
                        <Image
                          src={`https://openweathermap.org/img/w/${row.whether.icon}.png`}
                          width={40}
                          height={40}
                          alt="天気アイコン"
                        />
                      </TableCell>
                      <TableCell align="right">{row.whether.tem.max}</TableCell>
                      <TableCell align="right">{row.whether.tem.min}</TableCell>
                      <TableCell align="right">
                        {row.whether.tem.morn}
                      </TableCell>
                      <TableCell align="right">{row.whether.tem.day}</TableCell>
                      <TableCell align="right">
                        {row.whether.tem.night}
                      </TableCell>
                      <TableCell>{row.whether.sunrise}</TableCell>
                      <TableCell>{row.whether.sunset}</TableCell>
                    </TableRow>
                  </TableBody>
                </Table>
              </Box>
            </Collapse>
          </TableCell>
        </TableRow>
      )}
      <PlanEditModal
        isOpen={openEditModal}
        onClose={handleEditModalClose}
        plan={row}
      />
      <PlanDeleteConfirmModal
        isOpen={openDeleteConfirmModal}
        onClose={handleDeleteConfirmModalClose}
        planId={row.id}
      />
    </React.Fragment>
  );
};

export interface CollapsibleTableProps {
  planList: Plan[];
}
export const CollapsibleTable = ({ planList }: CollapsibleTableProps) => {
  return (
    <TableContainer component={Paper}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell />
            <TableCell>タイトル</TableCell>
            <TableCell>内容</TableCell>
            <TableCell>場所</TableCell>
            <TableCell>開始日</TableCell>
            <TableCell>終了日</TableCell>
            <TableCell />
          </TableRow>
        </TableHead>
        <TableBody>
          {planList.length > 0 &&
            planList.map((row) => <Row key={row.id} row={row} />)}
        </TableBody>
      </Table>
    </TableContainer>
  );
};
