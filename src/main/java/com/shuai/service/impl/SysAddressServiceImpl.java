package com.shuai.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.shuai.config.mq.RabbitMqHelper;
import com.shuai.constants.MqConstants;
import com.shuai.convert.SysAddressConvert;
import com.shuai.domain.dto.SysAddressQueryDTO;
import com.shuai.domain.po.SysAddress;
import com.shuai.domain.po.User;
import com.shuai.domain.vo.SysAddressVO;
import com.shuai.mapper.SysAddressMapper;
import com.shuai.service.SysAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @Author: KingCoding
 * @Date: 2025/5/20
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysAddressServiceImpl implements SysAddressService {

    private final SysAddressMapper sysAddressMapper;
    private final SysAddressConvert sysAddressConvert;
    private final RabbitMqHelper rabbitMqHelper;

    @Override
    public List<SysAddressVO> getAddressTree(SysAddressQueryDTO queryDTO) {
        String rootName = queryDTO.getName();
        BigDecimal lng = queryDTO.getLng();
        BigDecimal lat = queryDTO.getLat();
        Long code = queryDTO.getCode();
        List<SysAddress> sysAddressList = sysAddressMapper.selectSysAddressList();
        List<SysAddressVO> sysAddressVOList = sysAddressConvert.ETV(sysAddressList);

        if (ObjectUtil.isNotEmpty(rootName) || ObjectUtil.isNotEmpty(lng) || ObjectUtil.isNotEmpty(lat) || ObjectUtil.isNotEmpty(code)) {

            // 过滤出所有直接匹配条件的节点
            // 下面.filter中的筛选时进行了与或非的判断
            List<SysAddressVO> matchNodes = sysAddressVOList.stream()
                    .filter(entity ->
                            (ObjectUtil.isEmpty(rootName) || rootName.equals(entity.getName())) &&
                            (ObjectUtil.isEmpty(lng) || lng.equals(entity.getLng())) &&
                            (ObjectUtil.isEmpty(lat) || lat.equals(entity.getLat())) &&
                            (ObjectUtil.isEmpty(code) || code.equals(entity.getCode()))
                    )
                    .collect(Collectors.toList());

            if (CollUtil.isEmpty(matchNodes)) {
                return List.of();
            }

            // 收集所有需要包含在最终树中的节点ID (匹配节点 + 所有上级)
            Set<Long> nodeIdsToInclude = new HashSet<>();
            Map<Long, SysAddressVO> allNodesMap = sysAddressVOList.stream()
                    .collect(Collectors.toMap(SysAddressVO::getCode, Function.identity()));

            for (SysAddressVO matchNode : matchNodes) {
                Long currentNodeId = matchNode.getCode();
                // 添加匹配节点本身 (如果code不为null)
                if (currentNodeId != null) {
                    nodeIdsToInclude.add(currentNodeId);
                } else {
                    // 如果匹配节点的code为null，表示数据有问题，这里跳过或者记录日志
                    continue;
                }

                // 向上追溯父节点
                Long parentId = matchNode.getParentId();
                // 假设 2L 或 null 表示顶级节点的 parentId
                while (Objects.nonNull(parentId) && !parentId.equals(2L)) {
                    // 如果父节点ID已经被添加过，说明这条路径的上级已经处理过，可以提前终止
                    if (nodeIdsToInclude.contains(parentId)) {
                        break;
                    }
                    nodeIdsToInclude.add(parentId); // 添加当前父节点ID
                    // 判断当前父节点还有没有上级
                    parentId = allNodesMap.get(parentId).getParentId();
                }

                List<SysAddressVO> list = sysAddressVOList.stream()
                        .filter(node -> nodeIdsToInclude.contains(node.getCode()))
                        .toList();

                return buildTree(list, 3213L);
            }
        }
        return List.of();
    }

    @Override
    // @Async
    public void testAscn() {
        log.info("舆情建档开始...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("====舆情建档结束...");

        List<User> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            User user = new User().setId(i).setName("张三:" + i);
            list.add(user);
            // rabbitMqHelper.send(
            //         MqConstants.Exchange.USER_EXCHANGE,
            //         MqConstants.Key.USER_KEY,
            //         new User()
            //                 .setId(i)
            //                 .setName("张三:" + i));
            // rabbitMqHelper.send(
            //         MqConstants.Exchange.ORDER_EXCHANGE,
            //         MqConstants.Key.ORDER_KEY,
            //         new Order()
            //                 .setId(i)
            //                 .setOrderName(UUID.randomUUID().toString()));
        }

        List<User> list1 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User().setId(i).setName("张思:" + i);
            list1.add(user);
        }

        rabbitMqHelper.send(
                MqConstants.Exchange.USER_EXCHANGE,
                MqConstants.Key.USER_KEY,
                list1);
    }

    @Override
    public void test1(List<String> names) {
        List<SysAddress> sysAddressList = sysAddressMapper.selectAddressList(names);
        System.err.println(sysAddressList);
    }

    @Override
    public void test2(Set<String> names) {
        List<SysAddress> sysAddressList = sysAddressMapper.selectAddressList(names);
        System.err.println(sysAddressList);
    }

    public List<SysAddressVO> buildTree(List<SysAddressVO> list, Long rootId) {
        return TreeUtil.build(
                list,
                rootId,
                SysAddressVO::getCode,
                SysAddressVO::getParentId,
                SysAddressVO::setChildren
        );
    }
}