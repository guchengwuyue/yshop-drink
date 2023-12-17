export function formatTime(time) {
	if (typeof time !== 'number' || time < 0) {
		return time
	}

	var hour = parseInt(time / 3600)
	time = time % 3600
	var minute = parseInt(time / 60)
	time = time % 60
	var second = time

	return ([hour, minute, second]).map(function(n) {
		n = n.toString()
		return n[1] ? n : '0' + n
	}).join(':')
}

export function formatDateTime(date, fmt = 'yyyy-MM-dd hh:mm:ss') {
	if(!date) {
		return ''
	}
    if (typeof (date) === 'number') {
        date = new Date(date)
    }
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    }
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length))
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)))
    return fmt
}

export function formatLocation(longitude, latitude) {
	if (typeof longitude === 'string' && typeof latitude === 'string') {
		longitude = parseFloat(longitude)
		latitude = parseFloat(latitude)
	}

	longitude = longitude.toFixed(2)
	latitude = latitude.toFixed(2)

	return {
		longitude: longitude.toString().split('.'),
		latitude: latitude.toString().split('.')
	}
}

var dateUtils = {
	UNITS: {
		'年': 31557600000,
		'月': 2629800000,
		'天': 86400000,
		'小时': 3600000,
		'分钟': 60000,
		'秒': 1000
	},
	humanize: function(milliseconds) {
		var humanize = '';
		for (var key in this.UNITS) {
			if (milliseconds >= this.UNITS[key]) {
				humanize = Math.floor(milliseconds / this.UNITS[key]) + key + '前';
				break;
			}
		}
		return humanize || '刚刚';
	},
	format: function(dateStr) {
		var date = this.parse(dateStr)
		var diff = Date.now() - date.getTime();
		if (diff < this.UNITS['天']) {
			return this.humanize(diff);
		}
		var _format = function(number) {
			return (number < 10 ? ('0' + number) : number);
		};
		return date.getFullYear() + '/' + _format(date.getMonth() + 1) + '/' + _format(date.getDate()) + '-' +
			_format(date.getHours()) + ':' + _format(date.getMinutes());
	},
	parse: function(str) { //将"yyyy-mm-dd HH:MM:ss"格式的字符串，转化为一个Date对象
		var a = str.split(/[^0-9]/);
		return new Date(a[0], a[1] - 1, a[2], a[3], a[4], a[5]);
	}
};

// 返回上一页
export function prePage(page = null){
	let pages = getCurrentPages();
	//console.log('pages:',pages);
	let prePage = pages[pages.length - 2];
	if (page !== null) {
		prePage = pages[page];
	}
	// #ifdef H5
	return prePage;
	// #endif
	return prePage.$vm;
}

export function kmUnit(m){
    var v;
	if(typeof m === 'number' && !isNaN(m)){
		if (m >= 1000) {
            v = (m / 1000).toFixed(2) + 'km'
        } else {
           	v = m + 'm'
        }
	}else{
		v = '0m'
	}
    return v;
}

export function isWeixin() {
	if (navigator && navigator.userAgent && navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1) {
	  return true
	}
	return false
  }
  
export function parseQuery() {
    let res = {}
  
    // #ifdef H5
    const query = (location.href.split('?')[1] || '').trim().replace(/^(\?|#|&)/, '')
  
    if (!query) {
      return res
    }
  
    query.split('&').forEach(param => {
      const parts = param.replace(/\+/g, ' ').split('=')
      const key = decodeURIComponent(parts.shift())
      const val = parts.length > 0 ? decodeURIComponent(parts.join('=')) : null
  
      if (res[key] === undefined) {
        res[key] = val
      } else if (Array.isArray(res[key])) {
        res[key].push(val)
      } else {
        res[key] = [res[key], val]
      }
    })
    // #endif
    // #ifndef H5
    var pages = getCurrentPages() //获取加载的页面
    var currentPage = pages[pages.length - 1] //获取当前页面的对象
    var url = currentPage.route //当前页面url
    res = currentPage.options //如果要获取url中所带的参数可以查看options
    // #endif
  
    return res
  }
  

