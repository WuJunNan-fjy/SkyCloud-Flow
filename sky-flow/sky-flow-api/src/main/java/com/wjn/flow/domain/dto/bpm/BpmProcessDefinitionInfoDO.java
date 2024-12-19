package com.wjn.flow.domain.dto.bpm;

import com.wjn.common.core.domain.BaseEntity;
import com.wjn.flow.common.enums.definition.BpmModelFormTypeEnum;
import com.wjn.flow.common.enums.definition.BpmModelTypeEnum;
import com.wjn.flow.domain.dto.flow.FlwFormDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ProcessDefinition;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * BPM 流程定义的拓信息
 * 主要解决 Flowable {@link ProcessDefinition} 不支持拓展字段，所以新建该表
 *
 * @author wjn
 */
@Table(name = "bpm_process_definition_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmProcessDefinitionInfoDO extends BaseEntity {

    /**
     * 编号
     */
    @Id
    private Long id;
    /**
     * 流程定义的编号
     *
     * 关联 {@link ProcessDefinition#getId()} 属性
     */
    private String processDefinitionId;
    /**
     * 流程模型的编号
     *
     * 关联 {@link Model#getId()} 属性
     */
    private String modelId;
    /**
     * 流程模型的类型
     *
     * 枚举 {@link BpmModelTypeEnum}
     */
    private Integer modelType;

    /**
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    private String description;

    /**
     * 表单类型
     *
     * 枚举 {@link BpmModelFormTypeEnum}
     */
    private Integer formType;
    /**
     * 动态表单编号
     *
     * 在表单类型为 {@link BpmModelFormTypeEnum#NORMAL} 时
     *
     * 关联 {@link FlwFormDO#getId()}
     */
    private Long formId;
    /**
     * 表单的配置
     *
     * 在表单类型为 {@link BpmModelFormTypeEnum#NORMAL} 时
     *
     * 冗余 {@link FlwFormDO#getConf()}
     */
    private String formConf;
    /**
     * 表单项的数组
     *
     * 在表单类型为 {@link BpmModelFormTypeEnum#NORMAL} 时
     *
     * 冗余 {@link FlwFormDO#getFields()}
     */
    // @TableField("formFields")
    private List<String> formFields;
    /**
     * 自定义表单的提交路径，使用 Vue 的路由地址
     *
     * 在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时
     */
    private String formCustomCreatePath;
    /**
     * 自定义表单的查看路径，使用 Vue 的路由地址
     *
     * 在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时
     */
    private String formCustomViewPath;

    /**
     * SIMPLE 设计器模型数据 json 格式
     *
     * 目的：当使用仿钉钉设计器时。流程模型发布的时候，需要保存流程模型设计器的快照数据。
     */
    private String simpleModel;
    /**
     * 是否可见
     *
     * 目的：如果 false 不可见，则不展示在“发起流程”的列表里
     */
    private Boolean visible;

    /**
     * 可发起用户编号数组
     *
     * 关联 {@link AdminUserRespDTO#getId()} 字段的数组
     *
     * 如果为空，则表示“全部可以发起”！
     *
     * 它和 {@link #visible} 的区别在于：
     * 1. {@link #visible} 只是决定是否可见。即使不可见，还是可以发起
     * 2. startUserIds 决定某个用户是否可以发起。如果该用户不可发起，则他也是不可见的
     */
    // @TableField("startUserIds") // 为了可以使用 find_in_set 进行过滤
    private List<Long> startUserIds;

    /**
     * 可管理用户编号数组
     *
     * 关联 {@link AdminUserRespDTO#getId()} 字段的数组
     */
    // @TableField("managerUserIds") // 为了可以使用 find_in_set 进行过滤
    private List<Long> managerUserIds;

}
