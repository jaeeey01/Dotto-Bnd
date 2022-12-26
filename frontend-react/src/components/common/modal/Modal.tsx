import React, { ComponentProps, useRef } from 'react'
import ReactDOM from 'react-dom'
import './Modal.scss'

interface IModal extends ComponentProps<'div'> {
  isOpen: boolean
  onRequestClose?: () => void
  layerStyle?: React.CSSProperties
  cardStyle?: React.CSSProperties
}

export default function Modal(props: IModal) {
  const { isOpen, onRequestClose, layerStyle, cardStyle } = props
  const el = document.getElementById('modal') as HTMLElement

  const ele = useRef<any>(null)

  const onClickOutside = (e: React.MouseEvent) => {
    const { target } = e

    if (ele && !ele.current.contains(target)) {
      if (onRequestClose) onRequestClose()
    }
  }

  return isOpen
    ? ReactDOM.createPortal(
        <div
          className="modal__layer"
          onClick={onClickOutside}
          style={layerStyle}
        >
          <div className="modal__card" ref={ele} style={cardStyle}>
            {props.children}
          </div>
        </div>,
        el
      )
    : null
}
