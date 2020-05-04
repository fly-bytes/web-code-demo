package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import com.example.demo.config.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import org.springframework.web.bind.annotation.RestController;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import java.util.List;

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@Api(tags = "${table.comment!}")
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    @Autowired
    private ${table.serviceName} baseService;

    @GetMapping
    public ResponseMessage list(${entity} param) {
        List<${entity}> list = baseService.list(new QueryWrapper<>(param));
        return ResponseMessage.success(list);
    }

    @PostMapping
    public ResponseMessage saveOrUpdate(${entity} param) {
        boolean success = baseService.saveOrUpdate(param);
        return ResponseMessage.success(success);
    }

    @DeleteMapping
    public ResponseMessage delete(Integer id) {
        boolean success = baseService.removeById(id);
        return ResponseMessage.success(success);
    }

}
</#if>
