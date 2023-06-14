<#import "blocks/template.ftlh" as t>
<@t.template user>
<!-- неплохо бы еще фото -->
<div class="block-information">
    <h4>Все об складе:</h4>
    <div><b>Город:</b> ${storage.city}<br></div>
    <div><b>Описание:</b> ${storage.description}<br></div>
    <#if storage.contactInfo??>
        <div><b>Контактная информация:</b> ${storage.contactInfo}<br></div>
    </#if>
    <div><b>Название:</b> ${storage.title}<br></div>
    <div><b>Точный адресс:</b> ${storage.address}<br></div>
</div>

</@t.template>