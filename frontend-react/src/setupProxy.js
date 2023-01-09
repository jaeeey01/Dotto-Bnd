const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = (app) => {
  app.use(
    createProxyMiddleware('/api', {
      target: process.env.REACT_APP_HTTP_HOST || 'http://3.39.107.150',
      changeOrigin: true,
    })
  )
}
