let api = [];
api.push({
    alias: 'api',
    order: '1',
    desc: '学生管理 前端控制器',
    link: '学生管理_前端控制器',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '新增或者修改',
});
api[0].list.push({
    order: '2',
    desc: '单个删除',
});
api[0].list.push({
    order: '3',
    desc: '查询所有Student',
});
api[0].list.push({
    order: '4',
    desc: '获取单个',
});
api[0].list.push({
    order: '5',
    desc: '分页显示',
});
api[0].list.push({
    order: '6',
    desc: '批量删除',
});
api.push({
    alias: 'GeneratorController',
    order: '2',
    desc: '代码生成器前端控制器',
    link: '代码生成器前端控制器',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '一键生成前后端代码',
});
api.push({
    alias: 'MenuController',
    order: '3',
    desc: '菜单表 前端控制器',
    link: '菜单表_前端控制器',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '新增或者修改',
});
api[2].list.push({
    order: '2',
    desc: '单个删除',
});
api[2].list.push({
    order: '3',
    desc: '查询所有Menu',
});
api[2].list.push({
    order: '4',
    desc: '获取单个',
});
api[2].list.push({
    order: '5',
    desc: '分页显示',
});
api[2].list.push({
    order: '6',
    desc: '批量删除',
});
api[2].list.push({
    order: '7',
    desc: '获取用户菜单信息',
});
api[2].list.push({
    order: '8',
    desc: '获取用户路由信息',
});
api[2].list.push({
    order: '9',
    desc: '获取所有菜单信息并且构建树形结构',
});
api.push({
    alias: 'RoleController',
    order: '4',
    desc: '角色表 前端控制器',
    link: '角色表_前端控制器',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '新增或者修改',
});
api[3].list.push({
    order: '2',
    desc: '单个删除',
});
api[3].list.push({
    order: '3',
    desc: '查询所有Role',
});
api[3].list.push({
    order: '4',
    desc: '获取单个',
});
api[3].list.push({
    order: '5',
    desc: '分页显示',
});
api[3].list.push({
    order: '6',
    desc: '批量删除',
});
api[3].list.push({
    order: '7',
    desc: '获取该角色下的menu菜单id，返回到前端，添加到树形组件里面',
});
api[3].list.push({
    order: '8',
    desc: '重新给该名角色分配菜单权限',
});
api.push({
    alias: 'FileController',
    order: '5',
    desc: '文件上传前端控制器',
    link: '文件上传前端控制器',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '上传文件',
});
api[4].list.push({
    order: '2',
    desc: '文件下载',
});
api[4].list.push({
    order: '3',
    desc: '分页显示',
});
api[4].list.push({
    order: '4',
    desc: '修改文件',
});
api[4].list.push({
    order: '5',
    desc: '删除文件',
});
api[4].list.push({
    order: '6',
    desc: '批量删除',
});
api.push({
    alias: 'AopController',
    order: '6',
    desc: '',
    link: '',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '',
});
api.push({
    alias: 'EchartsController',
    order: '7',
    desc: '',
    link: '',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '获取系统数据并且显示出来',
});
api.push({
    alias: 'UserController',
    order: '8',
    desc: '用户表 前端控制器',
    link: '用户表_前端控制器',
    list: []
})
api[7].list.push({
    order: '1',
    desc: '新增用户',
});
api[7].list.push({
    order: '2',
    desc: '单个删除',
});
api[7].list.push({
    order: '3',
    desc: '查询所有User',
});
api[7].list.push({
    order: '4',
    desc: '获取单个',
});
api[7].list.push({
    order: '5',
    desc: '分页显示',
});
api[7].list.push({
    order: '6',
    desc: '批量删除',
});
api[7].list.push({
    order: '7',
    desc: '获取该名用户的角色',
});
api[7].list.push({
    order: '8',
    desc: '获取用户信息',
});
api[7].list.push({
    order: '9',
    desc: '修改密码',
});
api[7].list.push({
    order: '10',
    desc: '清除缓存',
});
api[7].list.push({
    order: '11',
    desc: '注册账户',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    alias: apiData.alias,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        alias: apiData.alias,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    if (apiData.length > 0) {
         for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="' + apiData[j].alias + '.html#header">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            let doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="' + apiData[j].alias + '.html#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}