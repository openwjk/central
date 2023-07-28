package com.openwjk.central.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CentralBatchTaskDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CentralBatchTaskDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("GMT_CREATE is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("GMT_CREATE is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("GMT_CREATE =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("GMT_CREATE <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("GMT_CREATE >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATE >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("GMT_CREATE <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATE <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("GMT_CREATE in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("GMT_CREATE not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATE between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATE not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("MODIFIER is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("MODIFIER is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("MODIFIER =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("MODIFIER <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("MODIFIER >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFIER >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("MODIFIER <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("MODIFIER <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("MODIFIER like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("MODIFIER not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("MODIFIER in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("MODIFIER not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("MODIFIER between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("MODIFIER not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("GMT_MODIFIED is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("GMT_MODIFIED is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("GMT_MODIFIED =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("GMT_MODIFIED <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("GMT_MODIFIED >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFIED >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("GMT_MODIFIED <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFIED <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("GMT_MODIFIED in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("GMT_MODIFIED not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFIED between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFIED not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("IS_DELETED is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("IS_DELETED is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(String value) {
            addCriterion("IS_DELETED =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(String value) {
            addCriterion("IS_DELETED <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(String value) {
            addCriterion("IS_DELETED >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DELETED >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(String value) {
            addCriterion("IS_DELETED <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(String value) {
            addCriterion("IS_DELETED <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLike(String value) {
            addCriterion("IS_DELETED like", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotLike(String value) {
            addCriterion("IS_DELETED not like", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<String> values) {
            addCriterion("IS_DELETED in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<String> values) {
            addCriterion("IS_DELETED not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(String value1, String value2) {
            addCriterion("IS_DELETED between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(String value1, String value2) {
            addCriterion("IS_DELETED not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("TASK_TYPE like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("TASK_TYPE not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andDateLabelIsNull() {
            addCriterion("DATE_LABEL is null");
            return (Criteria) this;
        }

        public Criteria andDateLabelIsNotNull() {
            addCriterion("DATE_LABEL is not null");
            return (Criteria) this;
        }

        public Criteria andDateLabelEqualTo(String value) {
            addCriterion("DATE_LABEL =", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotEqualTo(String value) {
            addCriterion("DATE_LABEL <>", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelGreaterThan(String value) {
            addCriterion("DATE_LABEL >", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelGreaterThanOrEqualTo(String value) {
            addCriterion("DATE_LABEL >=", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelLessThan(String value) {
            addCriterion("DATE_LABEL <", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelLessThanOrEqualTo(String value) {
            addCriterion("DATE_LABEL <=", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelLike(String value) {
            addCriterion("DATE_LABEL like", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotLike(String value) {
            addCriterion("DATE_LABEL not like", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelIn(List<String> values) {
            addCriterion("DATE_LABEL in", values, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotIn(List<String> values) {
            addCriterion("DATE_LABEL not in", values, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelBetween(String value1, String value2) {
            addCriterion("DATE_LABEL between", value1, value2, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotBetween(String value1, String value2) {
            addCriterion("DATE_LABEL not between", value1, value2, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeIsNull() {
            addCriterion("EARLY_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeIsNotNull() {
            addCriterion("EARLY_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeEqualTo(Date value) {
            addCriterion("EARLY_START_TIME =", value, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeNotEqualTo(Date value) {
            addCriterion("EARLY_START_TIME <>", value, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeGreaterThan(Date value) {
            addCriterion("EARLY_START_TIME >", value, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EARLY_START_TIME >=", value, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeLessThan(Date value) {
            addCriterion("EARLY_START_TIME <", value, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("EARLY_START_TIME <=", value, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeIn(List<Date> values) {
            addCriterion("EARLY_START_TIME in", values, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeNotIn(List<Date> values) {
            addCriterion("EARLY_START_TIME not in", values, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeBetween(Date value1, Date value2) {
            addCriterion("EARLY_START_TIME between", value1, value2, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andEarlyStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("EARLY_START_TIME not between", value1, value2, "earlyStartTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andResultExtIsNull() {
            addCriterion("RESULT_EXT is null");
            return (Criteria) this;
        }

        public Criteria andResultExtIsNotNull() {
            addCriterion("RESULT_EXT is not null");
            return (Criteria) this;
        }

        public Criteria andResultExtEqualTo(String value) {
            addCriterion("RESULT_EXT =", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtNotEqualTo(String value) {
            addCriterion("RESULT_EXT <>", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtGreaterThan(String value) {
            addCriterion("RESULT_EXT >", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_EXT >=", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtLessThan(String value) {
            addCriterion("RESULT_EXT <", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtLessThanOrEqualTo(String value) {
            addCriterion("RESULT_EXT <=", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtLike(String value) {
            addCriterion("RESULT_EXT like", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtNotLike(String value) {
            addCriterion("RESULT_EXT not like", value, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtIn(List<String> values) {
            addCriterion("RESULT_EXT in", values, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtNotIn(List<String> values) {
            addCriterion("RESULT_EXT not in", values, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtBetween(String value1, String value2) {
            addCriterion("RESULT_EXT between", value1, value2, "resultExt");
            return (Criteria) this;
        }

        public Criteria andResultExtNotBetween(String value1, String value2) {
            addCriterion("RESULT_EXT not between", value1, value2, "resultExt");
            return (Criteria) this;
        }

        public Criteria andRetryTimeIsNull() {
            addCriterion("RETRY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRetryTimeIsNotNull() {
            addCriterion("RETRY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRetryTimeEqualTo(Integer value) {
            addCriterion("RETRY_TIME =", value, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeNotEqualTo(Integer value) {
            addCriterion("RETRY_TIME <>", value, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeGreaterThan(Integer value) {
            addCriterion("RETRY_TIME >", value, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RETRY_TIME >=", value, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeLessThan(Integer value) {
            addCriterion("RETRY_TIME <", value, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeLessThanOrEqualTo(Integer value) {
            addCriterion("RETRY_TIME <=", value, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeIn(List<Integer> values) {
            addCriterion("RETRY_TIME in", values, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeNotIn(List<Integer> values) {
            addCriterion("RETRY_TIME not in", values, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeBetween(Integer value1, Integer value2) {
            addCriterion("RETRY_TIME between", value1, value2, "retryTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("RETRY_TIME not between", value1, value2, "retryTime");
            return (Criteria) this;
        }

        public Criteria andDeletePkIsNull() {
            addCriterion("DELETE_PK is null");
            return (Criteria) this;
        }

        public Criteria andDeletePkIsNotNull() {
            addCriterion("DELETE_PK is not null");
            return (Criteria) this;
        }

        public Criteria andDeletePkEqualTo(Long value) {
            addCriterion("DELETE_PK =", value, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkNotEqualTo(Long value) {
            addCriterion("DELETE_PK <>", value, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkGreaterThan(Long value) {
            addCriterion("DELETE_PK >", value, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkGreaterThanOrEqualTo(Long value) {
            addCriterion("DELETE_PK >=", value, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkLessThan(Long value) {
            addCriterion("DELETE_PK <", value, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkLessThanOrEqualTo(Long value) {
            addCriterion("DELETE_PK <=", value, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkIn(List<Long> values) {
            addCriterion("DELETE_PK in", values, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkNotIn(List<Long> values) {
            addCriterion("DELETE_PK not in", values, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkBetween(Long value1, Long value2) {
            addCriterion("DELETE_PK between", value1, value2, "deletePk");
            return (Criteria) this;
        }

        public Criteria andDeletePkNotBetween(Long value1, Long value2) {
            addCriterion("DELETE_PK not between", value1, value2, "deletePk");
            return (Criteria) this;
        }

        public Criteria andParamExtIsNull() {
            addCriterion("PARAM_EXT is null");
            return (Criteria) this;
        }

        public Criteria andParamExtIsNotNull() {
            addCriterion("PARAM_EXT is not null");
            return (Criteria) this;
        }

        public Criteria andParamExtEqualTo(String value) {
            addCriterion("PARAM_EXT =", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtNotEqualTo(String value) {
            addCriterion("PARAM_EXT <>", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtGreaterThan(String value) {
            addCriterion("PARAM_EXT >", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_EXT >=", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtLessThan(String value) {
            addCriterion("PARAM_EXT <", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtLessThanOrEqualTo(String value) {
            addCriterion("PARAM_EXT <=", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtLike(String value) {
            addCriterion("PARAM_EXT like", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtNotLike(String value) {
            addCriterion("PARAM_EXT not like", value, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtIn(List<String> values) {
            addCriterion("PARAM_EXT in", values, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtNotIn(List<String> values) {
            addCriterion("PARAM_EXT not in", values, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtBetween(String value1, String value2) {
            addCriterion("PARAM_EXT between", value1, value2, "paramExt");
            return (Criteria) this;
        }

        public Criteria andParamExtNotBetween(String value1, String value2) {
            addCriterion("PARAM_EXT not between", value1, value2, "paramExt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}