package com.example.mumuoa.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.mumuoa.config.SystemConstants;
import com.example.mumuoa.db.dao.CheckinMapper;
import com.example.mumuoa.db.dao.HolidaysMapper;
import com.example.mumuoa.db.dao.WorkdayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Slf4j
@Scope("prototype")
public class CheckinServiceImpl implements CheckinService {
    @Resource
    private SystemConstants constants;

    @Resource
    private HolidaysMapper holidaysMapper;

    @Resource
    private WorkdayMapper workdayMapper;

    @Resource
    private CheckinMapper checkinMapper;

    @Override
    public String validCanChecking(Integer userId, String date) {
        boolean todayIsHoliday = holidaysMapper.searchTodayIsHoliday() != null;
        boolean todayIsWorkday = workdayMapper.searchTodayIsWorkday() != null;
        String type = "工作日";
        if (DateUtil.date().isWeekend()) {
            type = "节假日";
        }
        if (todayIsHoliday) {
            type = "节假日";
        }
        if (todayIsWorkday) {
            type = "工作日";
        }
        if (type.equals("节假日")) {
            return "节假日不需要签到";
        } else {
            DateTime now = DateUtil.date();
            String startTimeStr = DateUtil.today() + " " + constants.attendanceStartTime;
            String endTimeStr = DateUtil.today() + " " + constants.attendanceEndTime;
            DateTime attendanceStartTime = DateUtil.parse(startTimeStr);
            DateTime attendanceEndTime = DateUtil.parse(endTimeStr);
            if (now.before(attendanceStartTime)) {
                return "还未到签到时间";
            } else if (now.after(attendanceEndTime)) {
                return "签到时间已结束";
            } else {
                HashMap map = new HashMap();
                map.put("userId", userId);
                map.put("date", date);
                map.put("start", startTimeStr);
                map.put("end", endTimeStr);
                boolean haveCheckin = checkinMapper.haveCheckin(map) != null;
                return haveCheckin ? "已签到，请勿重复签到" : "可以签到";
            }
        }
    }
}